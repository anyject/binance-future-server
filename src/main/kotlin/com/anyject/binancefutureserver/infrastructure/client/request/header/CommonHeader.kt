package com.anyject.binancefutureserver.infrastructure.client.request.header

import org.springframework.http.MediaType

data class CommonHeader(
    val contentType: String? = MediaType.APPLICATION_JSON.toString()
)