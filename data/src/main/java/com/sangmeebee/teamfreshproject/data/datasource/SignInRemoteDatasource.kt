package com.sangmeebee.teamfreshproject.data.datasource

import com.sangmeebee.teamfreshproject.domain.model.SignInRequest
import com.sangmeebee.teamfreshproject.domain.model.Token

interface SignInRemoteDatasource {
    suspend fun signIn(signInRequest: SignInRequest): Result<Token>
}
