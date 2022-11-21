package com.sangmeebee.teamfreshproject.data.model

import com.google.gson.annotations.SerializedName
import com.sangmeebee.teamfreshproject.data.model.mapper.DataToDomainMapper
import com.sangmeebee.teamfreshproject.domain.model.SignIn

data class SignInEntity(
    @SerializedName("userLoginId")
    val id: String,
    @SerializedName("userLoginPassword")
    val password: String,
) : DataToDomainMapper<SignIn> {
    override fun toDomain(): SignIn = SignIn(
        id = id,
        password = password
    )
}
