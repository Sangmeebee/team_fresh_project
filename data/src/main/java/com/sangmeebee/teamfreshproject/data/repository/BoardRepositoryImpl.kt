package com.sangmeebee.teamfreshproject.data.repository

import androidx.paging.PagingData
import com.sangmeebee.teamfreshproject.data.datasource.BoardRemoteDatasource
import com.sangmeebee.teamfreshproject.domain.model.Board
import com.sangmeebee.teamfreshproject.domain.model.BoardRequest
import com.sangmeebee.teamfreshproject.domain.repository.BoardRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class BoardRepositoryImpl @Inject constructor(
    private val boardRemoteDatasource: BoardRemoteDatasource,
) : BoardRepository {
    override fun getBoards(boardRequest: BoardRequest): Flow<PagingData<Board>> =
        boardRemoteDatasource.getBoards(boardRequest)
}
