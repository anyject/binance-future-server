package com.anyject.binancefutureserver.web.rest.exception

import jakarta.validation.ConstraintViolationException
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.core.MethodParameter
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.mock.web.MockHttpServletRequest
import org.springframework.validation.BindException
import org.springframework.validation.BindingResult
import org.springframework.validation.ObjectError
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes
import org.springframework.web.servlet.NoHandlerFoundException

class ExceptionAdviceTest {
    @Validated
    data class TestClass(val test: String)

    private val exceptionAdvice = ExceptionAdvice()

    @Test
    fun `handleBadRequestException - 400 Bad Request`() {
        // Given
        val request = MockHttpServletRequest()
        RequestContextHolder.setRequestAttributes(ServletRequestAttributes(request))

        val ex = IllegalArgumentException()
        val bodyOfResponse = "This should be application specific"

        // When
        val response = exceptionAdvice.handleBadRequestException(ex, request)

        // Then
        assertThat(response.statusCode).isEqualTo(HttpStatus.BAD_REQUEST)
        assertThat(response.body).isEqualTo(bodyOfResponse)
    }

    @Test
    fun `handleAnyException - 500 Internal Server Error`() {
        // Given
        val request = MockHttpServletRequest()
        RequestContextHolder.setRequestAttributes(ServletRequestAttributes(request))

        val ex = Exception()
        val bodyOfResponse = "This should be application specific"

        // When
        val response = exceptionAdvice.handleAnyException(ex, request)

        // Then
        assertThat(response.statusCode).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR)
        assertThat(response.body).isEqualTo(bodyOfResponse)
    }

    @Test
    fun `handleNoHandlerFoundException - 404 Not Found`() {
        // Given
        val request = MockHttpServletRequest()
        RequestContextHolder.setRequestAttributes(ServletRequestAttributes(request))

        val ex = NoHandlerFoundException("", "", HttpHeaders.EMPTY)

        // When
        val response = exceptionAdvice.handleNoHandlerFoundException(ex, request)

        // Then
        assertThat(response.statusCode).isEqualTo(HttpStatus.NOT_FOUND)
    }

    @Test
    fun `handleBindException - 400 Bad Request`() {
        // Given
        val request = MockHttpServletRequest()
        RequestContextHolder.setRequestAttributes(ServletRequestAttributes(request))
        val ex = BindException(TestClass::class.java, "test")
        val bodyOfResponse = "This should be application specific"

        // When
        val response = exceptionAdvice.handleBindException(ex, request)

        // Then
        assertThat(response.statusCode).isEqualTo(HttpStatus.BAD_REQUEST)
        assertThat(response.body).isEqualTo(bodyOfResponse)
    }

    @Test
    fun `handleConstraintViolationException - 400 Bad Request`() {
        // Given
        val request = MockHttpServletRequest()
        RequestContextHolder.setRequestAttributes(ServletRequestAttributes(request))

        val ex = ConstraintViolationException(null)
        val bodyOfResponse = "This should be application specific"

        // When
        val response = exceptionAdvice.handleConstraintViolationException(ex, request)

        // Then
        assertThat(response.statusCode).isEqualTo(HttpStatus.BAD_REQUEST)
        assertThat(response.body).isEqualTo(bodyOfResponse)
    }

    @Test
    fun `handleMethodArgumentNotValidException - 400 Bad Request`() {
        // Given
        val request = MockHttpServletRequest()
        RequestContextHolder.setRequestAttributes(ServletRequestAttributes(request))

        val testClass = TestClass("test")

        val methodParameter = Mockito.mock(MethodParameter::class.java)
        val bindingResult = Mockito.mock(BindingResult::class.java)

        Mockito.`when`(bindingResult.hasErrors()).thenReturn(true)
        Mockito.`when`(bindingResult.allErrors).thenReturn(listOf(ObjectError("test", "error")))

        val ex = MethodArgumentNotValidException(methodParameter, bindingResult)
        val bodyOfResponse = "This should be application specific"

        // When
        val response = exceptionAdvice.handleMethodArgumentNotValidException(ex, request)

        // Then
        assertThat(response.statusCode).isEqualTo(HttpStatus.BAD_REQUEST)
        assertThat(response.body).isEqualTo(bodyOfResponse)
    }
}

