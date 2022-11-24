package com.sangmeebee.teamfreshproject.data.model

import com.google.gson.annotations.SerializedName
import com.sangmeebee.teamfreshproject.data.model.mapper.DataToDomainMapper
import com.sangmeebee.teamfreshproject.domain.model.Board

internal data class BoardResponseEntity(
    val success: Boolean,
    val code: Int,
    @SerializedName("msg")
    val message: String,
    @SerializedName("recordsTotal")
    val totalCount: Long,
    @SerializedName("data")
    val data: List<BoardInfoResponseEntity>,
)

internal data class BoardInfoResponseEntity(
    @SerializedName("boardInfoSeqNo")
    val id: Long,
    @SerializedName("boardCn")
    val content: String,
    @SerializedName("rdcnt")
    val readCount: Long,
    @SerializedName("anscnt")
    val commentCount: Long,
    @SerializedName("userProfileFilePth")
    val profileUrl: String?,
    @SerializedName("wrterNcnm")
    val nickname: String?,
    @SerializedName("creatDt")
    val createDate: String,
) : DataToDomainMapper<Board> {
    override fun toDomain(): Board = Board(
        id = id,
        content = content,
        readCount = readCount,
        commentCount = commentCount,
        profileUrl = profileUrl,
        nickname = nickname,
        createDate = createDate
    )
}
