package com.sangmeebee.teamfreshproject.data.datasource

import androidx.paging.PagingSource
import com.google.common.truth.Truth.assertThat
import com.sangmeebee.teamfreshproject.data.model.BoardInfoResponseEntity
import com.sangmeebee.teamfreshproject.data.model.BoardRequestEntity
import com.sangmeebee.teamfreshproject.data.model.SearchBoardRequestEntity
import com.sangmeebee.teamfreshproject.data.service.BoardAPI
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File

class BoardPagingSourceTest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var boardAPI: BoardAPI

    private val expectedResponse = listOf(
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

    private val fakeResponseTotalCount = 2
    private val fakeRequest = BoardRequestEntity(pageSize = 1, SearchBoardRequestEntity(content = ""), start = 0)

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
    fun `데이터 요청에 성공하면 페이지 데이터를 반환한다`() = runTest {
        // given
        val response = MockResponse().setResponseCode(200).setBody(File("src/test/resources/board_200.json").readText())
        mockWebServer.enqueue(response)
        val pagingSource = BoardPagingSource(boardAPI, fakeRequest)
        val start = 0

        // when
        val actual = pagingSource.load(
            PagingSource.LoadParams.Refresh(
                key = null,
                loadSize = fakeRequest.pageSize,
                placeholdersEnabled = false
            )
        )

        // then
        val expected = PagingSource.LoadResult.Page(
            data = expectedResponse,
            prevKey = if (start == BoardPagingSource.STARTING_PAGE_INDEX) null else start - fakeRequest.pageSize,
            nextKey = if (fakeResponseTotalCount < (start + fakeRequest.pageSize).toLong()) null else start + fakeRequest.pageSize
        )
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `데이터 요청에 실패하면 예외를 반환한다`() = runTest {
        // given
        val response = MockResponse().setResponseCode(400)
        mockWebServer.enqueue(response)
        val pagingSource = BoardPagingSource(boardAPI, fakeRequest)

        // when
        val actual = pagingSource.load(
            PagingSource.LoadParams.Refresh(
                key = null,
                loadSize = fakeRequest.pageSize,
                placeholdersEnabled = false
            )
        )

        // then
        assertThat(actual).isInstanceOf(PagingSource.LoadResult.Error::class.java)
    }
}
