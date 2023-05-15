package com.anyject.binancefutureserver.infrastructure.client.future.rest.common

import org.springframework.http.MediaType

data class CommonHeader(
    val contentType: String? = MediaType.APPLICATION_JSON.toString()
)