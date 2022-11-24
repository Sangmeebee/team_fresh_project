package com.sangmeebee.teamfreshproject.model

data class BoardModel(
    val id: Long,
    val content: String,
    val readCount: Long,
    val commentCount: Long,
    val profileUrl: String?,
    val nickname: String?,
    val createDate: String,
)
