package com.sangmeebee.teamfreshproject.data.model.mapper

import com.sangmeebee.teamfreshproject.data.model.SignInEntity
import com.sangmeebee.teamfreshproject.domain.model.SignIn

fun SignIn.toData(): SignInEntity = SignInEntity(
    id = id,
    password = password
)
