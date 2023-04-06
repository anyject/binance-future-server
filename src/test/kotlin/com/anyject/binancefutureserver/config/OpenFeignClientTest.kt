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
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.HttpStatus

@ExtendWith(MockitoExtension::class)
class OpenFeignClientTest {

    @Test
    fun `OpenFeignClient Basic Connection Test`() {
        val client = OpenFeignConfig().getClient()
        assertThat(client.checkConnectionToServer().status()).isEqualTo(HttpStatus.OK.value())
    }
}

@EnableFeignClients
class OpenFeignConfig {
    val apiUrl = "https://testnet.binancefuture.com/fapi/v1"
    fun getClient() : BinanceFutureFeignClient {
        return Feign.builder()
            .client(OkHttpClient())
            .encoder(JacksonEncoder())
            .decoder(JacksonDecoder())
            .target(BinanceFutureFeignClient::class.java, apiUrl)
    }
}
@FeignClient(name = "BinanceFutureFeignClient", url = "https://testnet.binancefuture.com/fapi/v1")
interface BinanceFutureFeignClient {
    @RequestLine("GET /ping")
    fun checkConnectionToServer(): Response
}
