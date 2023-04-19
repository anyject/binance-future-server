package com.anyject.binancefutureserver.web.rest.exception

import jakarta.validation.constraints.NotBlank

data class TestDTO(
    @field:NotBlank
    val test: String
) {
    fun testMethod(testParam: String) {
        print(testParam)
    }
}