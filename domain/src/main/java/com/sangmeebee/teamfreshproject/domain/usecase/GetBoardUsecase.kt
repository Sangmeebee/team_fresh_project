package com.sangmeebee.teamfreshproject.domain.usecase

import androidx.paging.PagingData
import com.sangmeebee.teamfreshproject.domain.model.Board
import com.sangmeebee.teamfreshproject.domain.model.BoardRequest
import com.sangmeebee.teamfreshproject.domain.repository.BoardRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetBoardUsecase @Inject constructor(
    private val boardRepository: BoardRepository,
) {
    operator fun invoke(boardRequest: BoardRequest): Flow<PagingData<Board>> =
        boardRepository.getBoards(boardRequest)
}
