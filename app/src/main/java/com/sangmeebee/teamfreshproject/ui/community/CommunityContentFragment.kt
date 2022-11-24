package com.sangmeebee.teamfreshproject.ui.community

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.paging.LoadState
import com.sangmeebee.teamfreshproject.R
import com.sangmeebee.teamfreshproject.databinding.FragmentCommunityContentBinding
import com.sangmeebee.teamfreshproject.domain.util.HttpConnectionException
import com.sangmeebee.teamfreshproject.ui.base.BaseFragment
import com.sangmeebee.teamfreshproject.ui.community.adapter.CommunityContentAdapter
import com.sangmeebee.teamfreshproject.ui.community.adapter.FooterLoadStateAdapter
import com.sangmeebee.teamfreshproject.util.DividerDecoration
import com.sangmeebee.teamfreshproject.util.repeatOnStarted
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.distinctUntilChangedBy

@AndroidEntryPoint
class CommunityContentFragment : BaseFragment<FragmentCommunityContentBinding>(FragmentCommunityContentBinding::inflate) {

    private val communityContentViewModel by viewModels<CommunityContentViewModel>()
    private val communityContentAdapter = CommunityContentAdapter()

    override fun FragmentCommunityContentBinding.setBinding() {
        lifecycleOwner = this@CommunityContentFragment
        viewModel = communityContentViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpView()
        setUpObserve()
    }

    private fun setUpObserve() {
        observeBoards()
        observePagingRefresh()
    }

    private fun setUpView() {
        setRecyclerView()
        setSwipeRefreshLayout()
        setReloadClickListener()
    }

    private fun observePagingRefresh() = repeatOnStarted {
        communityContentAdapter.loadStateFlow
            .distinctUntilChangedBy { it.refresh }
            .collectLatest { loadStates ->
                when (val refreshLoadState = loadStates.refresh) {
                    is LoadState.Loading -> binding.srlLoading.isRefreshing = true
                    is LoadState.Error -> {
                        binding.srlLoading.isRefreshing = false
                        fetchUiState(true)
                        checkErrorState(refreshLoadState.error)
                    }
                    is LoadState.NotLoading -> {
                        communityContentViewModel.fetchErrorState(null)
                        binding.srlLoading.isRefreshing = false
                        fetchUiState(false)
                    }
                }
            }
    }

    private fun fetchUiState(isError: Boolean) {
        binding.includeReload.llReload.isVisible = isError
        binding.rvBoard.isVisible = !isError
    }

    private fun checkErrorState(throwable: Throwable) {
        when (throwable) {
            is HttpConnectionException -> communityContentViewModel.fetchErrorState(resources.getString(R.string.common_network_error))
            else -> communityContentViewModel.fetchErrorState(resources.getString(R.string.common_unknown_error))
        }
    }

    private fun observeBoards() = repeatOnStarted {
        communityContentViewModel.boards.distinctUntilChanged().collectLatest { boards ->
            communityContentAdapter.submitData(boards)
        }
    }

    private fun setRecyclerView() {
        binding.rvBoard.apply {
            adapter = communityContentAdapter
                .withLoadStateFooter(FooterLoadStateAdapter(communityContentAdapter::retry))
            addItemDecoration(DividerDecoration(1))
        }
    }

    private fun setSwipeRefreshLayout() {
        binding.srlLoading.apply {
            setOnRefreshListener {
                communityContentAdapter.refresh()
            }
        }
    }

    private fun setReloadClickListener() {
        binding.includeReload.llReload.setOnClickListener {
            communityContentAdapter.refresh()
        }
    }

    companion object {
        const val KEY_BOARD_SUBJECT = "boardSubject"
        const val DUMMY_CONTENT = ""
    }
}
