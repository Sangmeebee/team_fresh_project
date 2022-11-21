package com.sangmeebee.teamfreshproject.data.model

import com.google.gson.annotations.SerializedName

data class SignInEntity(
    @SerializedName("userLoginId")
    val id: String,
    @SerializedName("userLoginPassword")
    val password: String,
)
