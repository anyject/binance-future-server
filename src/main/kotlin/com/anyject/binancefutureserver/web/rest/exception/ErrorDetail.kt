package com.anyject.binancefutureserver.web.rest.exception

data class ErrorDetail(
    val field: String,
    val value: String,
    val message: String
)
