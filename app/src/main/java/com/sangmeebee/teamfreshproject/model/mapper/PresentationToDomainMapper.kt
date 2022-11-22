package com.sangmeebee.teamfreshproject.model.mapper

import com.sangmeebee.teamfreshproject.domain.model.SignInInfo
import com.sangmeebee.teamfreshproject.model.SignInInfoModel

internal fun SignInInfoModel.toDomain(): SignInInfo = SignInInfo(
    id = id,
    password = password
)
