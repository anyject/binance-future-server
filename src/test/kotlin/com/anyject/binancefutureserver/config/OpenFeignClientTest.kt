package com.anyject.binancefutureserver.config

import feign.Feign
import feign.RequestLine
import feign.Response
import feign.jackson.JacksonDecoder
import feign.jackson.JacksonEncoder
import feign.okhttp.OkHttpClient
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.http.HttpStatus

@ExtendWith(MockitoExtension::class)
class OpenFeignClientTest {

    @Test
    fun `OpenFeignClient Basic Connection Test`() {
        val apiUrl = "https://testnet.binancefuture.com/fapi/v1"
        val client = Feign.builder()
            .client(OkHttpClient())
            .encoder(JacksonEncoder())
            .decoder(JacksonDecoder())
            .target(BinanceFutureFeignClient::class.java, apiUrl)

        assertThat(client.checkConnectionToServer().status()).isEqualTo(HttpStatus.OK.value())
    }
}

interface BinanceFutureFeignClient {
    @RequestLine("GET /ping")
    fun checkConnectionToServer(): Response
}
