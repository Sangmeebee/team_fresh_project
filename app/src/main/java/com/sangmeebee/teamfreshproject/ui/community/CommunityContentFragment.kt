package com.sangmeebee.teamfreshproject.ui.community

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.sangmeebee.teamfreshproject.databinding.FragmentCommunityContentBinding
import com.sangmeebee.teamfreshproject.ui.base.BaseFragment
import com.sangmeebee.teamfreshproject.ui.community.adapter.CommunityContentAdapter
import com.sangmeebee.teamfreshproject.util.DividerDecoration
import com.sangmeebee.teamfreshproject.util.repeatOnStarted
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged

@AndroidEntryPoint
class CommunityContentFragment : BaseFragment<FragmentCommunityContentBinding>(FragmentCommunityContentBinding::inflate) {

    private val communityContentViewModel by viewModels<CommunityContentViewModel>()
    private val communityContentAdapter = CommunityContentAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerView()
        observeBoards()
    }

    private fun setRecyclerView() {
        binding.rvBoard.apply {
            adapter = communityContentAdapter
            addItemDecoration(DividerDecoration(1))
        }
    }

    private fun observeBoards() = repeatOnStarted {
        communityContentViewModel.boards.distinctUntilChanged().collectLatest { boards ->
            communityContentAdapter.submitData(boards)
        }
    }

    companion object {
        const val KEY_BOARD_SUBJECT = "boardSubject"
        const val DUMMY_CONTENT = ""
    }
}
