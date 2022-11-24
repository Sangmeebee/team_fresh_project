package com.sangmeebee.teamfreshproject.ui.sign_in

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.sangmeebee.teamfreshproject.R
import com.sangmeebee.teamfreshproject.databinding.FragmentSignInBinding
import com.sangmeebee.teamfreshproject.domain.util.HttpConnectionException
import com.sangmeebee.teamfreshproject.domain.util.IllegalIdException
import com.sangmeebee.teamfreshproject.domain.util.IllegalPasswordException
import com.sangmeebee.teamfreshproject.model.SignInRequestModel
import com.sangmeebee.teamfreshproject.ui.base.BaseFragment
import com.sangmeebee.teamfreshproject.util.repeatOnStarted
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map

@AndroidEntryPoint
class SignInFragment : BaseFragment<FragmentSignInBinding>(FragmentSignInBinding::inflate) {

    private val signInViewModel by viewModels<SignInViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpClickListener()
        setUpObserveUiState()
        setSwipeRefreshLayout()
    }

    private fun setUpClickListener() {
        binding.tvLogin.setOnClickListener {
            val id = binding.etId.text.toString()
            val password = binding.etPassword.text.toString()
            if (id.isBlank()) {
                showToast(resources.getString(R.string.sign_in_id_empty))
                return@setOnClickListener
            }
            if (password.isBlank()) {
                showToast(resources.getString(R.string.sign_in_password_empty))
                return@setOnClickListener
            }
            val signInRequestModel = SignInRequestModel(
                id = id,
                password = password
            )
            signInViewModel.signIn(signInRequestModel)
        }

        binding.tvFindId.setOnClickListener {
            showToast(resources.getString(R.string.sign_in_find_id))
        }

        binding.tvFindPw.setOnClickListener {
            showToast(resources.getString(R.string.sign_in_find_password))
        }

        binding.tvSignUp.setOnClickListener {
            showToast(resources.getString(R.string.sign_in_sign_up))
        }

        binding.ivKakaoLogin.setOnClickListener {
            showToast(resources.getString(R.string.sign_in_sign_in_kakao))
        }
    }

    private fun setSwipeRefreshLayout() {
        binding.srlLoading.isEnabled = false
    }

    private fun setUpObserveUiState() {
        observeError()
        observeIsSignIn()
        observeIsLoading()
    }

    private fun observeError() = repeatOnStarted {
        signInViewModel.uiState.map { it.error }.distinctUntilChanged().collectLatest { throwable ->
            if (throwable != null) {
                when (throwable) {
                    is IllegalIdException -> showToast(resources.getString(R.string.sign_in_id_error))
                    is IllegalPasswordException -> showToast(resources.getString(R.string.sign_in_password_error))
                    is HttpConnectionException -> showToast(resources.getString(R.string.common_network_error))
                    else -> showToast(resources.getString(R.string.sign_in_common_error))
                }
                signInViewModel.fetchError(null)
            }
        }
    }

    private fun observeIsLoading() = repeatOnStarted {
        signInViewModel.uiState.map { it.isLoading }.distinctUntilChanged().collectLatest {
            binding.srlLoading.isRefreshing = it
        }
    }

    private fun observeIsSignIn() = repeatOnStarted {
        signInViewModel.uiState.map { it.isSignIn }.distinctUntilChanged().collectLatest { isSignIn ->
            if (isSignIn) {
                val bundle = bundleOf(KEY_BUNDLE_SIGN_IN to true)
                findNavController().navigate(R.id.action_init, bundle)
            }
        }
    }

    companion object {
        const val KEY_BUNDLE_SIGN_IN = "isSignIn"
    }
}
