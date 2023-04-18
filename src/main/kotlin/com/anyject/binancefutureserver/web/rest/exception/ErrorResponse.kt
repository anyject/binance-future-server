package com.anyject.binancefutureserver.web.rest.exception

data class ErrorResponse(
    val status: Int,
    val message: String,
    val code: String,
    val errors: List<ErrorDetail> = emptyList()
)