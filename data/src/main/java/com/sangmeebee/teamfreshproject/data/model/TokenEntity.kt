package com.sangmeebee.teamfreshproject.data.model

import com.google.gson.annotations.SerializedName

data class TokenEntity(
    val success: Boolean,
    val code: Int,
    @SerializedName("msg")
    val message: String,
    @SerializedName("data")
    val token: String,
)
