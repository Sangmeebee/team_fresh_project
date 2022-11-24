package com.sangmeebee.teamfreshproject.data.model

data class ErrorResponseEntity(
    val success: Boolean,
    val code: Int,
    val message: String,
)
