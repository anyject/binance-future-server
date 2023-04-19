package com.anyject.binancefutureserver.web.rest.exception

import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.validation.Validator
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import org.springframework.validation.beanvalidation.MethodValidationInterceptor

@AutoConfigureMockMvc
@SpringBootTest
class ExceptionAdviceTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @Test
    fun `test IllegalArgumentException handling`() {
        mockMvc.perform(get("/api/exception/illegal-argument")).andExpect(status().isBadRequest)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("\$.message").value(ErrorCode.ILLEGAL_ARGUMENT.message))
    }

    @Test
    fun `test IllegalStatementException handling`() {
        mockMvc.perform(get("/api/exception/illegal-statement")).andExpect(status().isBadRequest)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("\$.message").value(ErrorCode.ILLEGAL_STATE.message))
    }

    @Test
    fun `test BusinessException handling`() {
        mockMvc.perform(get("/api/exception/business-exception")).andExpect(status().isInternalServerError)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("\$.message").value(ErrorCode.INTERNAL_SERVER_ERROR.message))
    }


    @Test
    fun `test UnexpectedException handling`() {
        mockMvc.perform(get("/api/exception/unexpected-exception")).andExpect(status().isInternalServerError)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("\$.message").value(ErrorCode.INTERNAL_SERVER_ERROR.message))
    }

    @Test
    fun `test NoHandlerFoundException handling`() {
        mockMvc.perform(get("/api/exception/no-handler-found")).andExpect(status().isNotFound)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("\$.message").value(ErrorCode.NOT_HANDLER_FOUND.message))
    }

    @Test
    fun `test MethodArgumentNotValidException handling`() {
        val content = objectMapper.writeValueAsString(TestDTO(""))
        mockMvc.perform(
            post("/api/exception/method-argument-not-valid")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(status().isBadRequest).andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("\$.message").value(ErrorCode.INVALID_INPUT_VALUE.message))
            .andExpect(jsonPath("\$.errors[0].field").value("test"))
            .andExpect(jsonPath("\$.errors[0].value").value(""))
    }

    @Test
    fun `test BindException handling`() {
        val content = objectMapper.writeValueAsString(TestDTO(""))
        mockMvc.perform(
            post("/api/exception/bind")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isBadRequest)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("\$.message").value(ErrorCode.INVALID_INPUT_VALUE.message))
            .andExpect(jsonPath("\$.errors[0].field").value("test"))
            .andExpect(jsonPath("\$.errors[0].value").value(""))
    }

    @Test
    fun `test ConstraintViolationException handling`() {
        val content = objectMapper.writeValueAsString(TestDTO(""))
        mockMvc.perform(
            post("/api/exception/constraint-violation")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(status().isBadRequest).andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("\$.message").value(ErrorCode.INVALID_INPUT_VALUE.message))
            .andExpect(jsonPath("\$.errors[0].field").value("test"))
            .andExpect(jsonPath("\$.errors[0].value").value(""))
    }
}

