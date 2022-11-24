package com.sangmeebee.teamfreshproject.data.model

import com.google.gson.annotations.SerializedName
import com.sangmeebee.teamfreshproject.data.model.mapper.DataToDomainMapper
import com.sangmeebee.teamfreshproject.domain.model.BoardRequest
import com.sangmeebee.teamfreshproject.domain.model.SearchBoardRequest

data class BoardRequestEntity(
    val length: Int,
    @SerializedName("searchObj")
    val searchBoard: SearchBoardRequestEntity,
    val start: Int,
) : DataToDomainMapper<BoardRequest> {
    override fun toDomain(): BoardRequest = BoardRequest(
        length = length,
        searchBoard = searchBoard.toDomain(),
        start = start
    )
}

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
) : DataToDomainMapper<SearchBoardRequest> {
    override fun toDomain(): SearchBoardRequest = SearchBoardRequest(
        content = content,
        subject = subject,
        type = type,
        createStart = createStart,
        createEnd = createEnd,
        writerId = writerId,
        writerNickname = writerNickname
    )
}
