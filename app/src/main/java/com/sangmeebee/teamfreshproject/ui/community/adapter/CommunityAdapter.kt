package com.sangmeebee.teamfreshproject.ui.community.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.sangmeebee.teamfreshproject.ui.community.CommunityContentFragment
import com.sangmeebee.teamfreshproject.ui.community.CommunityContentFragment.Companion.KEY_BOARD_SUBJECT

class CommunityAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = ITEM_COUNT

    override fun createFragment(position: Int): Fragment {
        val fragment = CommunityContentFragment()
        val tabSubjects = listOf(SUBJECT_1, SUBJECT_2, SUBJECT_3)
        fragment.arguments = Bundle().apply {
            putString(KEY_BOARD_SUBJECT, tabSubjects[position])
        }
        return fragment
    }

    companion object {
        private const val ITEM_COUNT = 3
        private const val SUBJECT_1 = "자유게시판"
        private const val SUBJECT_2 = "한줄평"
        private const val SUBJECT_3 = "영차TV"
    }
}
