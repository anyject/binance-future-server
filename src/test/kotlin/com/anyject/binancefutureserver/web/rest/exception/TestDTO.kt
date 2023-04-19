package com.anyject.binancefutureserver.web.rest.exception

import jakarta.validation.constraints.NotEmpty

data class TestDTO(
    @field:NotEmpty(message = "test is required")
    val test: String?
)