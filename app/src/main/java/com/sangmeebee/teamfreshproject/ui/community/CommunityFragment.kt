package com.sangmeebee.teamfreshproject.ui.community

import android.os.Bundle
import android.view.View
import com.google.android.material.tabs.TabLayoutMediator
import com.sangmeebee.teamfreshproject.R
import com.sangmeebee.teamfreshproject.databinding.FragmentCommunityBinding
import com.sangmeebee.teamfreshproject.ui.base.BaseFragment
import com.sangmeebee.teamfreshproject.ui.community.adapter.CommunityAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CommunityFragment : BaseFragment<FragmentCommunityBinding>(FragmentCommunityBinding::inflate) {

    private val communityAdapter by lazy { CommunityAdapter(this) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpView()
    }

    private fun setUpView() {
        setToolbar()
        setViewPager()
        setTabLayout()
        writeButtonClickListener()
    }

    private fun setToolbar() {
        binding.toolbar.apply {
            setNavigationOnClickListener {
                showToast(resources.getString(R.string.community_noti))
            }
            setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.profile -> {
                        showToast(resources.getString(R.string.community_profile))
                        return@setOnMenuItemClickListener true
                    }
                }
                false
            }
        }
    }

    private fun setTabLayout() {
        val tabSubjects = listOf(
            resources.getString(R.string.community_tab_1),
            resources.getString(R.string.community_tab_2),
            resources.getString(R.string.community_tab_3)
        )
        TabLayoutMediator(binding.tab, binding.viewPager) { tab, position ->
            tab.text = tabSubjects[position]
        }.attach()
    }

    private fun writeButtonClickListener() {
        binding.tvWrite.setOnClickListener {
            showToast(resources.getString(R.string.community_write))
        }
    }

    private fun setViewPager() {
        binding.viewPager.apply {
            adapter = communityAdapter
            offscreenPageLimit = 1
        }
    }
}
