package com.sangmeebee.teamfreshproject.domain.model

data class Board(
    val id: Long,
    val content: String,
    val readCount: Long,
    val commentCount: Long,
    val profileUrl: String?,
    val nickname: String?,
    val createDate: String,
)
