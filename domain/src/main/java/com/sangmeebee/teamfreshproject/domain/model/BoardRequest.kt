package com.sangmeebee.teamfreshproject.domain.model

data class BoardRequest(
    val length: Int,
    val searchBoard: SearchBoardRequest,
    val start: Int,
)

data class SearchBoardRequest(
    val content: String,
    val subject: String? = null,
    val type: String? = null,
    val createStart: String? = null,
    val createEnd: String? = null,
    val writerId: String? = null,
    val writerNickname: String? = null,
)
