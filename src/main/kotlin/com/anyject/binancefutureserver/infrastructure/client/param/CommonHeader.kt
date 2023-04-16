package com.anyject.binancefutureserver.infrastructure.client.param

import org.springframework.http.MediaType

data class CommonHeader(
    val contentType: String? = MediaType.APPLICATION_JSON.toString()
)