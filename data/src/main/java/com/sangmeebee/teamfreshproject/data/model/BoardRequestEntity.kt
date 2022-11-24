package com.sangmeebee.teamfreshproject.data.model

import com.google.gson.annotations.SerializedName
import com.sangmeebee.teamfreshproject.data.datasource.BoardPagingSource.Companion.PAGE_DISPLAY_SIZE
import com.sangmeebee.teamfreshproject.data.model.mapper.DataToDomainMapper
import com.sangmeebee.teamfreshproject.domain.model.BoardRequest

data class BoardRequestEntity(
    val length: Int = PAGE_DISPLAY_SIZE,
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
) : DataToDomainMapper<BoardRequest> {
    override fun toDomain(): BoardRequest = BoardRequest(
        content = content,
        subject = subject,
        type = type,
        createStart = createStart,
        createEnd = createEnd,
        writerId = writerId,
        writerNickname = writerNickname
    )
}
