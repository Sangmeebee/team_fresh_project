package com.sangmeebee.teamfreshproject.data.service

import com.google.common.truth.Truth.assertThat
import com.sangmeebee.teamfreshproject.data.model.BoardInfoResponseEntity
import com.sangmeebee.teamfreshproject.data.model.BoardRequestEntity
import com.sangmeebee.teamfreshproject.data.model.BoardResponseEntity
import com.sangmeebee.teamfreshproject.data.model.SearchBoardRequestEntity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File

@OptIn(ExperimentalCoroutinesApi::class)
class BoardAPITest {

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
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `게시판 정보를 불러온다`() = runTest {
        // given
        val response = MockResponse().setResponseCode(200).setBody(File("src/test/resources/board_200.json").readText())
        mockWebServer.enqueue(response)
        // when
        val actual = boardAPI.getBoards(BoardRequestEntity(length = 20, SearchBoardRequestEntity(content = ""), start = 0))
        // then
        val expected = BoardResponseEntity(
            success = true,
            code = 0,
            message = "성공하였습니다.",
            totalCount = 252,
            data = listOf(
                BoardInfoResponseEntity(
                    id = 2758,
                    content = "<div>\n 3\n</div>",
                    readCount = 4,
                    commentCount = 0,
                    profileUrl = "/profile/4/e76553b0-1d52-4f05-b04e-9f58718a4de2.jpg",
                    nickname = "하히후헤",
                    createDate = "2022-07-21 14:29:38"
                ),
                BoardInfoResponseEntity(
                    id = 2756,
                    content = "<div>\n 2\n</div>",
                    readCount = 2,
                    commentCount = 3,
                    profileUrl = "/profile/4/e76553b0-1d52-4f05-b04e-9f58718a4de2.jpg",
                    nickname = "하히후헤",
                    createDate = "2022-07-21 14:20:13"
                )
            )
        )
        assertThat(actual).isEqualTo(expected)
    }
}
