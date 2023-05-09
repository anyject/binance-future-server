package com.anyject.binancefutureserver.web.rest.exception

import com.anyject.binancefutureserver.utils.Slf4j
import com.anyject.binancefutureserver.utils.Slf4j.Companion.log
import jakarta.validation.ConstraintViolationException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.servlet.NoHandlerFoundException

@Slf4j
@RestControllerAdvice
class ExceptionAdvice {

    @ExceptionHandler(value = [IllegalArgumentException::class])
    fun handleIllegalArgumentException(e: IllegalArgumentException): ResponseEntity<ErrorResponse> {
        log.error("IllegalArgumentException", e)
        return ResponseEntity.badRequest()
            .body(ErrorResponse.of(ErrorCode.ILLEGAL_ARGUMENT))
    }

    @ExceptionHandler(value = [IllegalStateException::class])
    fun handleIllegalStateException(e: IllegalStateException): ResponseEntity<ErrorResponse> {
        log.error("IllegalStateException", e)
        return ResponseEntity.badRequest()
            .body(ErrorResponse.of(ErrorCode.ILLEGAL_STATE))
    }

    @ExceptionHandler(value = [NoHandlerFoundException::class])
    fun handleNoHandlerFoundException(e: NoHandlerFoundException): ResponseEntity<ErrorResponse> {
        log.error("NoHandlerFoundException", e)
        return ResponseEntity(
            ErrorResponse.of(ErrorCode.NOT_HANDLER_FOUND),
            HttpStatus.NOT_FOUND
        )
    }

    @ExceptionHandler(value = [BindException::class])
    fun handleBindException(e: BindException): ResponseEntity<ErrorResponse> {
        log.error("MethodArgumentNotValidException", e)
        return ResponseEntity.badRequest().body(ErrorResponse.of(ErrorCode.INVALID_INPUT_VALUE, e.bindingResult))
    }

    @ExceptionHandler(value = [ConstraintViolationException::class])
    fun handleConstraintViolationException(
        e: ConstraintViolationException
    ): ResponseEntity<ErrorResponse> {
        log.error("ConstraintViolationException", e)
        return ResponseEntity.badRequest()
            .body(
                ErrorResponse.of(
                    ErrorCode.INVALID_INPUT_VALUE,
                    ErrorDetail.of(e.constraintViolations)
                )
            )
    }

    @ExceptionHandler(value = [MethodArgumentNotValidException::class])
    fun handleMethodArgumentNotValidException(
        e: MethodArgumentNotValidException
    ): ResponseEntity<ErrorResponse> {
        log.error("MethodArgumentNotValidException", e)
        return ResponseEntity.badRequest().body(ErrorResponse.of(ErrorCode.INVALID_INPUT_VALUE, e.bindingResult))
    }

    @ExceptionHandler(value = [BusinessException::class])
    fun handleBusinessException(e: BusinessException): ResponseEntity<ErrorResponse> {
        log.error("BusinessException", e)
        return ResponseEntity.internalServerError()
            .body(ErrorResponse.of(e.errorCode))
    }

    @ExceptionHandler(value = [Exception::class])
    fun handleAnyException(e: Exception): ResponseEntity<ErrorResponse> {
        log.error("UnExpected Exception", e)
        return ResponseEntity.internalServerError()
            .body(ErrorResponse.of(ErrorCode.INTERNAL_SERVER_ERROR))
    }
}
