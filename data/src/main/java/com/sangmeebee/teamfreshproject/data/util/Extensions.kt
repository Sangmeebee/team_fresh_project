package com.sangmeebee.teamfreshproject.data.util

import com.google.gson.Gson
import com.sangmeebee.teamfreshproject.data.model.ErrorResponseEntity
import retrofit2.Response

fun <T> Response<T>.getResult(handleError: (Int) -> Throwable): Result<T> = if (isSuccessful) {
    val responseBody = requireNotNull(body())
    Result.success(responseBody)
} else {
    val errorBody = errorBody()?.string()
    val errorResponse = Gson().fromJson(errorBody, ErrorResponseEntity::class.java)
    Result.failure(handleError(errorResponse.code))
}
