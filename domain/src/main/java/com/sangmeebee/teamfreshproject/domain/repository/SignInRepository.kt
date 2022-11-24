package com.sangmeebee.teamfreshproject.domain.repository

import com.sangmeebee.teamfreshproject.domain.model.SignInRequest
import com.sangmeebee.teamfreshproject.domain.model.Token

interface SignInRepository {
    suspend fun signIn(signInRequest: SignInRequest): Result<Token>
}
