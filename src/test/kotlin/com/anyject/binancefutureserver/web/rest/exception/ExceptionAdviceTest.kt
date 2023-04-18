package com.anyject.binancefutureserver.web.rest.exception

import jakarta.validation.ConstraintViolationException
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Disabled
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
    fun `handleIllegalArgumentException - 400 Bad Request`() {
        // Given
        val request = MockHttpServletRequest()
        RequestContextHolder.setRequestAttributes(ServletRequestAttributes(request))

        val ex = IllegalArgumentException()

        // When
        val response = exceptionAdvice.handleIllegalArgumentException(ex)

        // Then
        assertThat(response.statusCode).isEqualTo(HttpStatus.BAD_REQUEST)
        assertThat(response.body).isInstanceOf(ErrorResponse::class.java)
    }

    @Test
    fun `handleIllegalStateException - 400 Bad Request`() {
        // Given
        val request = MockHttpServletRequest()
        RequestContextHolder.setRequestAttributes(ServletRequestAttributes(request))

        val ex = IllegalStateException()

        // When
        val response = exceptionAdvice.handleIllegalStateException(ex)

        // Then
        assertThat(response.statusCode).isEqualTo(HttpStatus.BAD_REQUEST)
        assertThat(response.body).isInstanceOf(ErrorResponse::class.java)
    }

    @Test
    fun `handleAnyException - 500 Internal Server Error`() {
        // Given
        val request = MockHttpServletRequest()
        RequestContextHolder.setRequestAttributes(ServletRequestAttributes(request))

        val ex = Exception()

        // When
        val response = exceptionAdvice.handleAnyException(ex)

        // Then
        assertThat(response.statusCode).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR)
        assertThat(response.body).isInstanceOf(ErrorResponse::class.java)
    }

    @Test
    fun `handleNoHandlerFoundException - 404 Not Found`() {
        // Given
        val request = MockHttpServletRequest()
        RequestContextHolder.setRequestAttributes(ServletRequestAttributes(request))

        val ex = NoHandlerFoundException("", "", HttpHeaders.EMPTY)

        // When
        val response = exceptionAdvice.handleNoHandlerFoundException(ex)

        // Then
        assertThat(response.statusCode).isEqualTo(HttpStatus.NOT_FOUND)
        assertThat(response.body).isInstanceOf(ErrorResponse::class.java)
    }

    @Test
    fun `handleBindException - 400 Bad Request`() {
        // Given
        val request = MockHttpServletRequest()
        RequestContextHolder.setRequestAttributes(ServletRequestAttributes(request))
        val ex = BindException(TestClass::class.java, "test")

        // When
        val response = exceptionAdvice.handleBindException(ex)

        // Then
        assertThat(response.statusCode).isEqualTo(HttpStatus.BAD_REQUEST)
        assertThat(response.body).isInstanceOf(ErrorResponse::class.java)
    }

    @Test
    @Disabled
    fun `handleConstraintViolationException - 400 Bad Request`() {
        // Given
        val request = MockHttpServletRequest()
        RequestContextHolder.setRequestAttributes(ServletRequestAttributes(request))

        val ex = ConstraintViolationException(null)

        // When
        val response = exceptionAdvice.handleConstraintViolationException(ex)

        // Then
        assertThat(response.statusCode).isEqualTo(HttpStatus.BAD_REQUEST)
        assertThat(response.body).isInstanceOf(ErrorResponse::class.java)
    }

    @Test
    @Disabled
    fun `handleMethodArgumentNotValidException - 400 Bad Request`() {
        // Given
        val request = MockHttpServletRequest()
        RequestContextHolder.setRequestAttributes(ServletRequestAttributes(request))

        val methodParameter = Mockito.mock(MethodParameter::class.java)
        val bindingResult = Mockito.mock(BindingResult::class.java)

        Mockito.`when`(bindingResult.hasErrors()).thenReturn(true)
        Mockito.`when`(bindingResult.allErrors).thenReturn(listOf(ObjectError("test", "error")))

        val ex = MethodArgumentNotValidException(methodParameter, bindingResult)

        // When
        val response = exceptionAdvice.handleMethodArgumentNotValidException(ex)

        // Then
        assertThat(response.statusCode).isEqualTo(HttpStatus.BAD_REQUEST)
        assertThat(response.body).isInstanceOf(ErrorResponse::class.java)
    }
}

