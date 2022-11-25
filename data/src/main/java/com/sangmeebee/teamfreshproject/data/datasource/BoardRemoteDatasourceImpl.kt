package com.sangmeebee.teamfreshproject.data.datasource

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.sangmeebee.teamfreshproject.data.model.BoardInfoResponseEntity
import com.sangmeebee.teamfreshproject.data.model.mapper.toData
import com.sangmeebee.teamfreshproject.data.service.BoardAPI
import com.sangmeebee.teamfreshproject.domain.model.Board
import com.sangmeebee.teamfreshproject.domain.model.BoardRequest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class BoardRemoteDatasourceImpl @Inject constructor(
    private val boardAPI: BoardAPI,
) : BoardRemoteDatasource {
    override fun getBoards(boardRequest: BoardRequest): Flow<PagingData<Board>> = Pager(
        config = PagingConfig(
            pageSize = boardRequest.pageSize,
            enablePlaceholders = false
        ),
        pagingSourceFactory = { BoardPagingSource(boardAPI, boardRequest.toData()) }
    ).flow.map { pagingData: PagingData<BoardInfoResponseEntity> ->
        pagingData.map { boardResponseEntity ->
            boardResponseEntity.toDomain()
        }
    }
}
