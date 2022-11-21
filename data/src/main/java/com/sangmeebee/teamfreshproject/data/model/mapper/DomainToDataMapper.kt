package com.sangmeebee.teamfreshproject.data.model.mapper

import com.sangmeebee.teamfreshproject.data.model.SignInInfoEntity
import com.sangmeebee.teamfreshproject.domain.model.SignInInfo

fun SignInInfo.toData(): SignInInfoEntity = SignInInfoEntity(
    id = id,
    password = password
)
