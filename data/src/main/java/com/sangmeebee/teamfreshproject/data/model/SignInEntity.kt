package com.sangmeebee.teamfreshproject.data.model

import com.google.gson.annotations.SerializedName

data class SignInEntity(
    @SerializedName("userLoginId")
    val userLoginId: String,
    @SerializedName("userLoginPassword")
    val userLoginPassword: String,
)
