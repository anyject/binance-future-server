package com.anyject.binancefutureserver.web.rest.exception

import com.anyject.binancefutureserver.utils.Slf4j
import jakarta.servlet.http.HttpServletRequest
import jakarta.validation.ConstraintViolationException
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.servlet.NoHandlerFoundException

@Slf4j
@RestControllerAdvice
class ExceptionAdvice {

    @ExceptionHandler(value = [IllegalArgumentException::class, IllegalStateException::class])
    fun handleBadRequestException(ex: RuntimeException, request: HttpServletRequest): ResponseEntity<Any> {
        val bodyOfResponse = "This should be application specific"
        return ResponseEntity.badRequest().body(bodyOfResponse)
    }

    @ExceptionHandler(value = [NoHandlerFoundException::class])
    fun handleNoHandlerFoundException(ex: NoHandlerFoundException, request: HttpServletRequest): ResponseEntity<Any> {
        val bodyOfResponse = "This should be application specific"
        return ResponseEntity.notFound().build()
    }

    @ExceptionHandler(value = [BindException::class])
    fun handleBindException(ex: BindException, request: HttpServletRequest): ResponseEntity<Any> {
        val bodyOfResponse = "This should be application specific"
        return ResponseEntity.badRequest().body(bodyOfResponse)
    }

    @ExceptionHandler(value = [ConstraintViolationException::class])
    fun handleConstraintViolationException(
        ex: ConstraintViolationException,
        request: HttpServletRequest
    ): ResponseEntity<Any> {
        val bodyOfResponse = "This should be application specific"
        return ResponseEntity.badRequest().body(bodyOfResponse)
    }

    @ExceptionHandler(value = [MethodArgumentNotValidException::class])
    fun handleMethodArgumentNotValidException(
        ex: MethodArgumentNotValidException,
        request: HttpServletRequest
    ): ResponseEntity<Any> {
        val bodyOfResponse = "This should be application specific"
        return ResponseEntity.badRequest().body(bodyOfResponse)
    }

    @ExceptionHandler(value = [Exception::class])
    fun handleAnyException(ex: Exception, request: HttpServletRequest): ResponseEntity<Any> {
        val bodyOfResponse = "This should be application specific"
        return ResponseEntity.internalServerError().body(bodyOfResponse)
    }
}
