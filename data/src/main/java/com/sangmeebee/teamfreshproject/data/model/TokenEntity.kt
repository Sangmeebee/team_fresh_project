package com.sangmeebee.teamfreshproject.data.model

import com.google.gson.annotations.SerializedName
import com.sangmeebee.teamfreshproject.data.model.mapper.DataToDomainMapper
import com.sangmeebee.teamfreshproject.domain.model.Token

data class TokenEntity(
    val success: Boolean,
    val code: Int,
    @SerializedName("msg")
    val message: String,
    @SerializedName("data")
    val token: String? = null,
) : DataToDomainMapper<Token> {
    override fun toDomain(): Token = Token(token!!)
}
