package com.sangmeebee.teamfreshproject.data.model

import com.google.gson.annotations.SerializedName

data class BoardRequestEntity(
    @SerializedName("length")
    val pageSize: Int,
    @SerializedName("searchObj")
    val searchBoard: SearchBoardRequestEntity,
    val start: Int = 0,
)

data class SearchBoardRequestEntity(
    @SerializedName("boardCn")
    val content: String,
    @SerializedName("boardSj")
    val subject: String? = null,
    @SerializedName("boardTy")
    val type: String? = null,
    @SerializedName("creatDStart")
    val createStart: String? = null,
    @SerializedName("creatDEnd")
    val createEnd: String? = null,
    @SerializedName("wrterLoginId")
    val writerId: String? = null,
    @SerializedName("wrterNcnm")
    val writerNickname: String? = null,
)
