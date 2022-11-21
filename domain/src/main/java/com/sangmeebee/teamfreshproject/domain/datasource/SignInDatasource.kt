package com.sangmeebee.teamfreshproject.domain.datasource

import com.sangmeebee.teamfreshproject.domain.model.SignIn
import com.sangmeebee.teamfreshproject.domain.model.Token

interface SignInDatasource {
    suspend fun signIn(signIn: SignIn): Result<Token>
}
