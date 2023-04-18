package com.anyject.binancefutureserver.web.rest.exception

data class ErrorResponse(
    val status: Int,
    val code: String,
    val message: String,
    val errors: List<ErrorDetail> = emptyList()
)