package com.anyject.binancefutureserver.web.rest.exception

import jakarta.validation.Valid
import org.springframework.http.HttpHeaders
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.NoHandlerFoundException

@RestController
@RequestMapping("/api/exception")
class ExceptionAdviceTestController {
    @GetMapping("/illegal-argument")
    fun illegalArgumentException(): Unit = throw IllegalArgumentException()

    @GetMapping("/illegal-statement")
    fun illegalStateException(): Unit = throw IllegalStateException()

    @GetMapping("/business-exception")
    fun businessException(): Unit = throw BusinessException(ErrorCode.INTERNAL_SERVER_ERROR)

    @GetMapping("/unexpected-exception")
    fun unknownException(): Unit = throw Exception()

    @GetMapping("/no-handler-found")
    fun noHandlerFoundException(): Unit =
        throw NoHandlerFoundException("GET", "/api/exception/no-handler-found", HttpHeaders.EMPTY)

    @PostMapping("/method-argument-not-valid")
    fun methodArgumentNotValidException(@Valid @RequestBody testDTO: TestDTO) = Unit

    @PostMapping("/bind")
    fun bindException(@Valid @RequestBody testDTO: TestDTO) = Unit

    @PostMapping("/constraint-violation")
    fun constraintViolationException(@Valid @RequestBody testDTO: TestDTO) = Unit
}