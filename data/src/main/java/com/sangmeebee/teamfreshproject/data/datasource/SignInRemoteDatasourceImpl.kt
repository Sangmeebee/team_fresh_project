package com.sangmeebee.teamfreshproject.data.datasource

import com.sangmeebee.teamfreshproject.data.di.IoDispatcher
import com.sangmeebee.teamfreshproject.data.model.mapper.toData
import com.sangmeebee.teamfreshproject.data.service.SignInAPI
import com.sangmeebee.teamfreshproject.data.util.getResult
import com.sangmeebee.teamfreshproject.domain.model.SignInInfo
import com.sangmeebee.teamfreshproject.domain.model.Token
import com.sangmeebee.teamfreshproject.domain.util.HttpConnectionException
import com.sangmeebee.teamfreshproject.domain.util.ID_EXCEPTION_CODE
import com.sangmeebee.teamfreshproject.domain.util.IllegalIdException
import com.sangmeebee.teamfreshproject.domain.util.IllegalPasswordException
import com.sangmeebee.teamfreshproject.domain.util.PASSWORD_EXCEPTION_CODE
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class SignInRemoteDatasourceImpl @Inject constructor(
    private val signInAPI: SignInAPI,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
) : SignInRemoteDatasource {

    override suspend fun signIn(signInInfo: SignInInfo): Result<Token> = try {
        withContext(dispatcher) {
            val response = signInAPI.signIn(signInInfo.toData())
            response.getResult { code ->
                when (code) {
                    ID_EXCEPTION_CODE -> IllegalIdException()
                    PASSWORD_EXCEPTION_CODE -> IllegalPasswordException()
                    else -> IllegalArgumentException()
                }
            }.map { it.toDomain() }
        }
    } catch (e: Exception) {
        Result.failure(HttpConnectionException())
    }
}
