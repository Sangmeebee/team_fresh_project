package com.sangmeebee.teamfreshproject.ui.home

import android.os.Bundle
import androidx.navigation.fragment.findNavController
import com.sangmeebee.teamfreshproject.R
import com.sangmeebee.teamfreshproject.databinding.FragmentEmptyBinding
import com.sangmeebee.teamfreshproject.ui.base.BaseFragment
import com.sangmeebee.teamfreshproject.ui.sign_in.SignInFragment.Companion.KEY_BUNDLE_SIGN_IN

class HomeFragment : BaseFragment<FragmentEmptyBinding>(FragmentEmptyBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val isSignIn = arguments?.getBoolean(KEY_BUNDLE_SIGN_IN)
        if (isSignIn == null || !isSignIn) {
            findNavController().navigate(R.id.action_sign_in)
        }
    }
}
