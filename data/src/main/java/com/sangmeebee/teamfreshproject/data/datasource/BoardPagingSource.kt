package com.sangmeebee.teamfreshproject.data.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.sangmeebee.teamfreshproject.data.model.BoardInfoResponseEntity
import com.sangmeebee.teamfreshproject.data.model.BoardRequestEntity
import com.sangmeebee.teamfreshproject.data.service.BoardAPI
import com.sangmeebee.teamfreshproject.domain.util.HttpConnectionException
import retrofit2.HttpException
import java.io.IOException

internal class BoardPagingSource(
    private val boardAPI: BoardAPI,
    private val boardRequestEntity: BoardRequestEntity,
) : PagingSource<Int, BoardInfoResponseEntity>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, BoardInfoResponseEntity> {
        try {
            val start = params.key ?: STARTING_PAGE_INDEX
            val newRequest = boardRequestEntity.copy(start = start)
            val response = boardAPI.getBoards(newRequest)
            return LoadResult.Page(
                data = response.data,
                prevKey = if (start == STARTING_PAGE_INDEX) null else start - PAGE_DISPLAY_SIZE, // Only paging forward.
                nextKey = if (response.totalCount < (start + PAGE_DISPLAY_SIZE).toLong()) null else start + PAGE_DISPLAY_SIZE
            )
        } catch (e: IOException) {
            // IOException for network failures.
            return LoadResult.Error(HttpConnectionException())
        } catch (e: HttpException) {
            // HttpException for any non-2xx HTTP status codes.
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, BoardInfoResponseEntity>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(PAGE_DISPLAY_SIZE)
                ?: anchorPage?.nextKey?.minus(PAGE_DISPLAY_SIZE)
        }
    }

    companion object {
        const val STARTING_PAGE_INDEX = 0
        const val PAGE_DISPLAY_SIZE = 20
    }
}
