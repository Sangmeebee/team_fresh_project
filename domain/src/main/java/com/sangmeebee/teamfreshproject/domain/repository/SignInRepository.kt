package com.sangmeebee.teamfreshproject.domain.repository

import com.sangmeebee.teamfreshproject.domain.model.SignInInfo
import com.sangmeebee.teamfreshproject.domain.model.Token

interface SignInRepository {
    suspend fun signIn(signInInfo: SignInInfo): Result<Token>
}
