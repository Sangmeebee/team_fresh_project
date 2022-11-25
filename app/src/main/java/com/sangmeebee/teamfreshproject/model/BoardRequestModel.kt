package com.sangmeebee.teamfreshproject.model

data class BoardRequestModel(
    val pageSize: Int = 20,
    val content: String,
    val subject: String? = null,
    val type: String? = null,
    val createStart: String? = null,
    val createEnd: String? = null,
    val writerId: String? = null,
    val writerNickname: String? = null,
)
