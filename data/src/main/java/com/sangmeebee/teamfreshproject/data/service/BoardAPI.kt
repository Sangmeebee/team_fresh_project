package com.sangmeebee.teamfreshproject.data.service

import com.sangmeebee.teamfreshproject.data.model.BoardRequestEntity
import com.sangmeebee.teamfreshproject.data.model.BoardResponseEntity
import retrofit2.http.Body
import retrofit2.http.POST

internal interface BoardAPI {

    @POST("v1/free-boards/Dt")
    suspend fun getBoards(
        @Body boardRequestEntity: BoardRequestEntity,
    ): BoardResponseEntity
}
