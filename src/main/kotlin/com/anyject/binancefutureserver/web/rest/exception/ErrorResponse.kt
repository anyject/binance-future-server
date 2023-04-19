package com.anyject.binancefutureserver.web.rest.exception

import org.springframework.validation.BindingResult
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException

data class ErrorResponse(
    val status: Int,
    val code: String,
    val message: String,
    val errors: List<ErrorDetail> = emptyList()
) {
    constructor(errorCode: ErrorCode) : this(
        errorCode.status,
        errorCode.code,
        errorCode.message
    )

    constructor(errorCode: ErrorCode, errors: List<ErrorDetail>) : this(
        errorCode.status,
        errorCode.code,
        errorCode.message,
        errors
    )

    companion object {
        fun of(code: ErrorCode, bindingResult: BindingResult): ErrorResponse {
            return ErrorResponse(code, ErrorDetail.of(bindingResult))
        }

        fun of(code: ErrorCode): ErrorResponse {
            return ErrorResponse(code)
        }

        fun of(code: ErrorCode, errors: List<ErrorDetail>): ErrorResponse {
            return ErrorResponse(code, errors)
        }

        fun of(e: MethodArgumentTypeMismatchException): ErrorResponse {
            val value = if (e.value == null) "" else e.value.toString()
            val errors: List<ErrorDetail> = ErrorDetail.of(e.name, value, e.errorCode)
            return ErrorResponse(ErrorCode.INTERNAL_SERVER_ERROR, errors)
        }
    }
}