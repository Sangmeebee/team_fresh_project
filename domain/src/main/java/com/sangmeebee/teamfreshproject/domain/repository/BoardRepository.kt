package com.sangmeebee.teamfreshproject.domain.repository

import androidx.paging.PagingData
import com.sangmeebee.teamfreshproject.domain.model.Board
import com.sangmeebee.teamfreshproject.domain.model.BoardRequest
import kotlinx.coroutines.flow.Flow

interface BoardRepository {
    fun getBoards(boardRequest: BoardRequest): Flow<PagingData<Board>>
}
