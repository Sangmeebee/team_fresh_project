package com.sangmeebee.teamfreshproject.model.mapper

import com.sangmeebee.teamfreshproject.domain.model.BoardRequest
import com.sangmeebee.teamfreshproject.domain.model.SignInRequest
import com.sangmeebee.teamfreshproject.model.BoardRequestModel
import com.sangmeebee.teamfreshproject.model.SignInRequestModel

internal fun SignInRequestModel.toDomain(): SignInRequest = SignInRequest(
    id = id,
    password = password
)

internal fun BoardRequestModel.toDomain(): BoardRequest = BoardRequest(
    content = content,
    subject = subject,
    type = type,
    createStart = createStart,
    createEnd = createEnd,
    writerId = writerId,
    writerNickname = writerNickname
)
