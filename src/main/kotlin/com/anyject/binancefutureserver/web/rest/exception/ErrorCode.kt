package com.anyject.binancefutureserver.web.rest.exception

import org.springframework.http.HttpStatus

enum class ErrorCode(
    val status: Int,
    val code: String,
    val message: String
) {
    INVALID_INPUT_VALUE(HttpStatus.BAD_REQUEST.value(), "INVALID_INPUT_VALUE", "Invalid Input Value"),
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED.value(), "METHOD_NOT_ALLOWED", "Method Not Allowed"),
    ILLEGAL_ARGUMENT(HttpStatus.BAD_REQUEST.value(), "ILLEGAL_ARGUMENT", "Illegal Argument"),
    ILLEGAL_STATE(HttpStatus.BAD_REQUEST.value(), "ILLEGAL_STATE", "Illegal State"),
    NOT_HANDLER_FOUND(HttpStatus.NOT_FOUND.value(), "NOT_HANDLER_FOUND", "Not Handler Found"),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(), "INTERNAL_SERVER_ERROR", "Internal Server Error"),
    INVALID_TYPE_VALUE(HttpStatus.BAD_REQUEST.value(), "INVALID_TYPE_VALUE", "Invalid Type Value")
}