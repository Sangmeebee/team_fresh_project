package com.sangmeebee.teamfreshproject.domain.usecase

import com.sangmeebee.teamfreshproject.domain.model.BoardRequest
import com.sangmeebee.teamfreshproject.domain.repository.BoardRepository
import io.mockk.coVerify
import io.mockk.mockk
import org.junit.Test

class GetBoardUsecaseTest {
    @Test
    fun `게시판 정보를 요청한다`() {
        // given
        val boardRepository: BoardRepository = mockk(relaxed = true)
        val getBoardUsecase = GetBoardUsecase(boardRepository)
        val boardRequest = BoardRequest(pageSize = 20, content = "")
        // when
        getBoardUsecase(boardRequest)
        // then
        coVerify { boardRepository.getBoards(boardRequest) }
    }
}
