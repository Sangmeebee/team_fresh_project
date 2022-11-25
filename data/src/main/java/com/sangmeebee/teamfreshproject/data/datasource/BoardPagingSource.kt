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
                prevKey = if (start == STARTING_PAGE_INDEX) null else start - boardRequestEntity.pageSize, // Only paging forward.
                nextKey = if (response.totalCount < (start + boardRequestEntity.pageSize).toLong()) null else start + boardRequestEntity.pageSize
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
            anchorPage?.prevKey?.plus(boardRequestEntity.pageSize)
                ?: anchorPage?.nextKey?.minus(boardRequestEntity.pageSize)
        }
    }

    companion object {
        const val STARTING_PAGE_INDEX = 0
    }
}
