package com.sangmeebee.teamfreshproject.data.service

import com.sangmeebee.teamfreshproject.data.model.SignInInfoEntity
import com.sangmeebee.teamfreshproject.data.model.TokenEntity
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

internal interface SignInAPI {

    @POST("v1/signIn")
    suspend fun signIn(
        @Body signInInfoEntity: SignInInfoEntity,
    ): Response<TokenEntity>
}
