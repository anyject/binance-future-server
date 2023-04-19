package com.anyject.binancefutureserver.web.rest.exception

import jakarta.validation.ConstraintViolation
import org.springframework.validation.BindingResult
import org.springframework.validation.FieldError
import java.util.stream.Collectors


data class ErrorDetail(
    val field: String,
    val value: String,
    val message: String
) {
    companion object {
        fun of(field: String, value: String, reason: String): List<ErrorDetail> {
            val fieldErrors: MutableList<ErrorDetail> = ArrayList()
            fieldErrors.add(ErrorDetail(field, value, reason))
            return fieldErrors
        }

        fun of(bindingResult: BindingResult): List<ErrorDetail> {
            val fieldErrors = bindingResult.fieldErrors
            return fieldErrors.stream()
                .map { error: FieldError ->
                    ErrorDetail(
                        error.field,
                        if (error.rejectedValue == null) "" else error.rejectedValue.toString(),
                        error.defaultMessage!!
                    )
                }
                .collect(Collectors.toList())
        }

        fun of(constraintViolations: Set<ConstraintViolation<*>>): List<ErrorDetail> {
            return constraintViolations.map { error ->
                ErrorDetail(
                    error.propertyPath.toString(),
                    if (error.invalidValue == null) "" else error.invalidValue.toString(),
                    error.message
                )
            }
        }
    }
}
