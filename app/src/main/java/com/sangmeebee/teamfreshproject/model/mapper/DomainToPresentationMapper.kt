package com.sangmeebee.teamfreshproject.model.mapper

import com.sangmeebee.teamfreshproject.domain.model.Board
import com.sangmeebee.teamfreshproject.model.BoardModel
import com.sangmeebee.teamfreshproject.util.convertDateFormat

private const val DATE_FORMAT_PATTERN_1 = "yyyy-MM-dd"
private const val DATE_FORMAT_PATTERN_2 = "yy.MM.dd"

internal fun Board.toPresentation(): BoardModel = BoardModel(
    id = id,
    content = content,
    readCount = readCount,
    commentCount = commentCount,
    profileUrl = profileUrl,
    nickname = nickname,
    createDate = createDate.convertDateFormat(DATE_FORMAT_PATTERN_1, DATE_FORMAT_PATTERN_2)
)
