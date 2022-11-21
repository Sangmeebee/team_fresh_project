package com.sangmeebee.teamfreshproject.data.datasource

import com.sangmeebee.teamfreshproject.data.di.IoDispatcher
import com.sangmeebee.teamfreshproject.data.model.mapper.toData
import com.sangmeebee.teamfreshproject.data.service.SignInAPI
import com.sangmeebee.teamfreshproject.domain.model.SignInInfo
import com.sangmeebee.teamfreshproject.domain.model.Token
import com.sangmeebee.teamfreshproject.domain.util.ID_EXCEPTION_CODE
import com.sangmeebee.teamfreshproject.domain.util.IllegalIdException
import com.sangmeebee.teamfreshproject.domain.util.IllegalPasswordException
import com.sangmeebee.teamfreshproject.domain.util.PASSWORD_EXCEPTION_CODE
import com.sangmeebee.teamfreshproject.domain.util.SUCCESS_CODE
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class SignInRemoteDatasourceImpl @Inject constructor(
    private val signInAPI: SignInAPI,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
) : SignInRemoteDatasource {
    override suspend fun signIn(signInInfo: SignInInfo): Result<Token> =
        withContext(dispatcher) {
            val response = signInAPI.signIn(signInInfo.toData())
            when (response.code) {
                SUCCESS_CODE -> runCatching {
                    requireNotNull(response.token)
                    response.toDomain()
                }
                ID_EXCEPTION_CODE -> Result.failure(IllegalIdException())
                PASSWORD_EXCEPTION_CODE -> Result.failure(IllegalPasswordException())
                else -> Result.failure(IllegalArgumentException())
            }
        }
}