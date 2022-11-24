package com.sangmeebee.teamfreshproject.ui.sign_in

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sangmeebee.teamfreshproject.domain.usecase.SignInUsecase
import com.sangmeebee.teamfreshproject.model.SignInRequestModel
import com.sangmeebee.teamfreshproject.model.SignInUiState
import com.sangmeebee.teamfreshproject.model.mapper.toDomain
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val signInUsecase: SignInUsecase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(SignInUiState())
    val uiState = _uiState.asStateFlow()

    fun signIn(signInRequest: SignInRequestModel) = viewModelScope.launch {
        _uiState.update { it.copy(isLoading = true) }
        signInUsecase(signInRequest.toDomain())
            .onSuccess { _uiState.update { it.copy(isSignIn = true) } }
            .onFailure { throwable -> fetchError(throwable) }
        _uiState.update { it.copy(isLoading = false) }
    }

    fun fetchError(throwable: Throwable?) {
        _uiState.update { it.copy(error = throwable) }
    }
}
