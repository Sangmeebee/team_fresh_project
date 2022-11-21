package com.sangmeebee.teamfreshproject.data.service

import com.sangmeebee.teamfreshproject.data.model.SignInEntity
import com.sangmeebee.teamfreshproject.data.model.TokenEntity
import retrofit2.http.Body
import retrofit2.http.POST

internal interface SignInAPI {

    @POST("v1/signIn")
    suspend fun signIn(
        @Body signInEntity: SignInEntity,
    ): TokenEntity
}
