package com.sangmeebee.teamfreshproject.data.repository

import com.sangmeebee.teamfreshproject.data.datasource.BoardRemoteDatasource
import com.sangmeebee.teamfreshproject.domain.model.BoardRequest
import com.sangmeebee.teamfreshproject.domain.repository.BoardRepository
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test

class BoardRepositoryImplTest {
    @Test
    fun `게시판 정보를 요청한다`() = runTest {
        // given
        val boardRemoteDatasource: BoardRemoteDatasource = mockk(relaxed = true)
        val boardRepository: BoardRepository = BoardRepositoryImpl(boardRemoteDatasource)
        val boardRequest = BoardRequest(pageSize = 20, content = "")
        // when
        boardRepository.getBoards(boardRequest)
        // then
        coVerify { boardRemoteDatasource.getBoards(boardRequest) }
    }
}
