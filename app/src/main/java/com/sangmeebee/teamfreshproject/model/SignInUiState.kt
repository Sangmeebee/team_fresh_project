package com.sangmeebee.teamfreshproject.model

data class SignInUiState(
    val isLoading: Boolean = false,
    val error: Throwable? = null,
    val isSignIn: Boolean = false,
)
