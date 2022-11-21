package com.sangmeebee.teamfreshproject.data.repository

import com.sangmeebee.teamfreshproject.data.datasource.SignInRemoteDatasource
import com.sangmeebee.teamfreshproject.domain.model.SignInInfo
import com.sangmeebee.teamfreshproject.domain.model.Token
import com.sangmeebee.teamfreshproject.domain.repository.SignInRepository
import javax.inject.Inject

internal class SignInRepositoryImpl @Inject constructor(
    private val signInRemoteDatasource: SignInRemoteDatasource,
) : SignInRepository {
    override suspend fun signIn(signInInfo: SignInInfo): Result<Token> = signInRemoteDatasource.signIn(signInInfo)
}
