package com.sangmeebee.teamfreshproject.data.model.mapper

import com.sangmeebee.teamfreshproject.data.model.BoardRequestEntity
import com.sangmeebee.teamfreshproject.data.model.SearchBoardRequestEntity
import com.sangmeebee.teamfreshproject.data.model.SignInInfoEntity
import com.sangmeebee.teamfreshproject.domain.model.BoardRequest
import com.sangmeebee.teamfreshproject.domain.model.SearchBoardRequest
import com.sangmeebee.teamfreshproject.domain.model.SignInInfo

internal fun SignInInfo.toData(): SignInInfoEntity = SignInInfoEntity(
    id = id,
    password = password
)

internal fun BoardRequest.toData(): BoardRequestEntity = BoardRequestEntity(
    length = length,
    searchBoard = searchBoard.toData(),
    start = start
)

internal fun SearchBoardRequest.toData(): SearchBoardRequestEntity = SearchBoardRequestEntity(
    content = content,
    subject = subject,
    type = type,
    createStart = createStart,
    createEnd = createEnd,
    writerId = writerId,
    writerNickname = writerNickname
)
