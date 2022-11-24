package com.sangmeebee.teamfreshproject.data.datasource

import androidx.paging.PagingData
import com.sangmeebee.teamfreshproject.domain.model.Board
import com.sangmeebee.teamfreshproject.domain.model.BoardRequest
import kotlinx.coroutines.flow.Flow

interface BoardRemoteDatasource {
    fun getBoards(boardRequest: BoardRequest): Flow<PagingData<Board>>
}
