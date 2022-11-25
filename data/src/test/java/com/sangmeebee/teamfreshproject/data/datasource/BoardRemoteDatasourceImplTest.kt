package com.sangmeebee.teamfreshproject.data.datasource

import androidx.paging.PagingData
import com.google.common.truth.Truth.assertThat
import com.sangmeebee.teamfreshproject.data.service.BoardAPI
import com.sangmeebee.teamfreshproject.domain.model.BoardRequest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File

class BoardRemoteDatasourceImplTest {

    private lateinit var boardRemoteDatasource: BoardRemoteDatasource
    private lateinit var mockWebServer: MockWebServer
    private lateinit var boardAPI: BoardAPI

    @Before
    fun setUp() {
        mockWebServer = MockWebServer()
        mockWebServer.start()
        boardAPI = Retrofit.Builder()
            .baseUrl(mockWebServer.url(""))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BoardAPI::class.java)

        boardRemoteDatasource = BoardRemoteDatasourceImpl(boardAPI)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `게시판 정보를 요청하면 flow로 래핑된 페이징 객체를 반환한다`() = runTest {
        // given
        val response = MockResponse().setResponseCode(200).setBody(File("src/test/resources/board_200.json").readText())
        mockWebServer.enqueue(response)
        // when
        val actual = boardRemoteDatasource.getBoards(BoardRequest(pageSize = 2, content = ""))
        // then
        assertThat(actual.first()).isInstanceOf(PagingData::class.java)
    }
}
