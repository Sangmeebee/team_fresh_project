package com.sangmeebee.teamfreshproject.data.datasource

import com.sangmeebee.teamfreshproject.domain.model.SignInInfo
import com.sangmeebee.teamfreshproject.domain.model.Token

interface SignInDatasource {
    suspend fun signIn(signInInfo: SignInInfo): Result<Token>
}
