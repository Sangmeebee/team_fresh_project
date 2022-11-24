package com.sangmeebee.teamfreshproject.model

data class BoardRequestModel(
    val length: Int,
    val searchBoard: SearchBoardRequestModel,
    val start: Int,
)

data class SearchBoardRequestModel(
    val content: String,
    val subject: String? = null,
    val type: String? = null,
    val createStart: String? = null,
    val createEnd: String? = null,
    val writerId: String? = null,
    val writerNickname: String? = null,
)
