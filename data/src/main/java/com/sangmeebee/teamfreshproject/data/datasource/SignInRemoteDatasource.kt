package com.sangmeebee.teamfreshproject.data.datasource

import com.sangmeebee.teamfreshproject.domain.model.SignInInfo
import com.sangmeebee.teamfreshproject.domain.model.Token

interface SignInRemoteDatasource {
    suspend fun signIn(signInInfo: SignInInfo): Result<Token>
}
