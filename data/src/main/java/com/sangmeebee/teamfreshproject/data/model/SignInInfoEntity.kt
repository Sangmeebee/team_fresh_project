package com.sangmeebee.teamfreshproject.data.model

import com.google.gson.annotations.SerializedName
import com.sangmeebee.teamfreshproject.data.model.mapper.DataToDomainMapper
import com.sangmeebee.teamfreshproject.domain.model.SignInInfo

data class SignInInfoEntity(
    @SerializedName("userLoginId")
    val id: String,
    @SerializedName("userLoginPassword")
    val password: String,
) : DataToDomainMapper<SignInInfo> {
    override fun toDomain(): SignInInfo = SignInInfo(
        id = id,
        password = password
    )
}
