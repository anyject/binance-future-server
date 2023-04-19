package com.anyject.binancefutureserver.web.rest.exception

import jakarta.validation.ConstraintViolation
import jakarta.validation.ConstraintViolationException
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.core.MethodParameter
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.mock.web.MockHttpServletRequest
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.validation.BindException
import org.springframework.validation.BindingResult
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes
import org.springframework.web.servlet.NoHandlerFoundException
import java.lang.reflect.Method

@ExtendWith(MockitoExtension::class)
class ExceptionAdviceTest {

    private val exceptionAdvice = ExceptionAdvice()

    private val mockMvc = MockMvcBuilders.standaloneSetup(exceptionAdvice).build()

    @Mock
    private lateinit var bindingResult: BindingResult

    @Test
    fun `test MethodArgumentNotValidException handling`() {
        val fieldName = "testParam"
        val errorMessage = "testParam must not be empty"
        val invalidValue = ""
        val fieldError = FieldError("TestClass", fieldName, invalidValue, false, null, null, errorMessage)
        val method: Method = TestDTO::class.java.getMethod("testMethod", String::class.java)
        val methodParameter = MethodParameter(method, 0)

        // BindingResult에 에러 추가
        `when`(bindingResult.hasErrors()).thenReturn(true)
        `when`(bindingResult.fieldErrors).thenReturn(listOf(fieldError))

        // 예외 발생
        assertThatThrownBy {
            exceptionAdvice.handleMethodArgumentNotValidException(
                MethodArgumentNotValidException(
                    methodParameter,
                    bindingResult
                )
            )
        }
    }

    @Test
    fun `test ConstraintViolationException handling`() {
        val errorMessage = "must be greater than or equal to 0"
        val constraintViolation: ConstraintViolation<Any> =
            mock(ConstraintViolation::class.java) as ConstraintViolation<Any>

        // ConstraintViolation 객체에 에러 추가
        `when`(constraintViolation.message).thenReturn(errorMessage)

        // 예외 발생
        assertThatThrownBy {
            exceptionAdvice.handleConstraintViolationException(ConstraintViolationException(setOf(constraintViolation)))
        }
    }

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
        val ex = BindException(TestDTO::class.java, "test")

        // When
        val response = exceptionAdvice.handleBindException(ex)

        // Then
        assertThat(response.statusCode).isEqualTo(HttpStatus.BAD_REQUEST)
        assertThat(response.body).isInstanceOf(ErrorResponse::class.java)
    }
}

