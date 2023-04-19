package com.anyject.binancefutureserver.web.rest.exception


data class BusinessException(val errorCode: ErrorCode, override val message: String) :
    RuntimeException(message) {
    constructor(errorCode: ErrorCode) : this(errorCode, errorCode.message)
}