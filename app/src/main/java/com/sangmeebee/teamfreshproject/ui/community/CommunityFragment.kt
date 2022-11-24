package com.sangmeebee.teamfreshproject.ui.community

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.sangmeebee.teamfreshproject.databinding.FragmentCommunityBinding
import com.sangmeebee.teamfreshproject.ui.base.BaseFragment
import com.sangmeebee.teamfreshproject.ui.community.adapter.CommunityAdapter
import com.sangmeebee.teamfreshproject.util.repeatOnStarted
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged

@AndroidEntryPoint
class CommunityFragment : BaseFragment<FragmentCommunityBinding>(FragmentCommunityBinding::inflate) {

    private val communityViewModel by viewModels<CommunityViewModel>()
    private val communityAdapter = CommunityAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerView()
        observeBoards()
    }

    private fun setRecyclerView() {
        binding.rvBoard.adapter = communityAdapter
    }

    private fun observeBoards() = repeatOnStarted {
        communityViewModel.boards.distinctUntilChanged().collectLatest { boards ->
            communityAdapter.submitData(boards)
        }
    }
}
