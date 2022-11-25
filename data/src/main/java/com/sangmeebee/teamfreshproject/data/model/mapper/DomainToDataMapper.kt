package com.sangmeebee.teamfreshproject.data.model.mapper

import com.sangmeebee.teamfreshproject.data.model.BoardRequestEntity
import com.sangmeebee.teamfreshproject.data.model.SearchBoardRequestEntity
import com.sangmeebee.teamfreshproject.data.model.SignInRequestEntity
import com.sangmeebee.teamfreshproject.domain.model.BoardRequest
import com.sangmeebee.teamfreshproject.domain.model.SignInRequest

internal fun SignInRequest.toData(): SignInRequestEntity = SignInRequestEntity(
    id = id,
    password = password
)

internal fun BoardRequest.toData(): BoardRequestEntity = BoardRequestEntity(
    pageSize = pageSize,
    searchBoard = SearchBoardRequestEntity(
        content = content,
        subject = subject,
        type = type,
        createStart = createStart,
        createEnd = createEnd,
        writerId = writerId,
        writerNickname = writerNickname
    )
)
