package com.sangmeebee.teamfreshproject.model.mapper

import com.sangmeebee.teamfreshproject.domain.model.BoardRequest
import com.sangmeebee.teamfreshproject.domain.model.SearchBoardRequest
import com.sangmeebee.teamfreshproject.domain.model.SignInInfo
import com.sangmeebee.teamfreshproject.model.BoardRequestModel
import com.sangmeebee.teamfreshproject.model.SearchBoardRequestModel
import com.sangmeebee.teamfreshproject.model.SignInInfoModel

internal fun SignInInfoModel.toDomain(): SignInInfo = SignInInfo(
    id = id,
    password = password
)

internal fun BoardRequestModel.toDomain(): BoardRequest = BoardRequest(
    length = length,
    searchBoard = searchBoard.toDomain(),
    start = start
)

internal fun SearchBoardRequestModel.toDomain(): SearchBoardRequest = SearchBoardRequest(
    content = content,
    subject = subject,
    type = type,
    createStart = createStart,
    createEnd = createEnd,
    writerId = writerId,
    writerNickname = writerNickname
)
