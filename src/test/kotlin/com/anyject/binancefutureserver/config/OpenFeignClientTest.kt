package com.anyject.binancefutureserver.config

import feign.Feign
import feign.QueryMap
import feign.RequestLine
import feign.Response
import feign.jackson.JacksonDecoder
import feign.jackson.JacksonEncoder
import feign.okhttp.OkHttpClient
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.HttpStatus

@ExtendWith(MockitoExtension::class)
class OpenFeignClientTest {
    private lateinit var client: BinanceFutureFeignClient


    @BeforeEach
    fun init() {
        client = OpenFeignConfig().getClient();
    }


    @Test
    fun `OpenFeignClient Basic Connection Test`() {
        assertThat(client.checkConnectionToServer().status()).isEqualTo(HttpStatus.OK.value())
    }

    @Test
    fun `Send klines request and retrieve response body`() {
        val queryMap = HashMap<String, Any>()
        queryMap["symbol"] = "BTCUSDT"
        queryMap["interval"] = "5m"
        queryMap["limit"] = 750
        val response = client.retrieveKline(queryMap)
        assertThat(response.status()).isEqualTo(HttpStatus.OK.value())
        assertThat(response.body()).isNotNull
    }
    
}
@EnableFeignClients
class OpenFeignConfig {
    val apiUrl = "https://testnet.binancefuture.com/fapi/v1"
    fun getClient(): BinanceFutureFeignClient {
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

    @RequestLine("GET /klines")
    fun retrieveKline(
       @QueryMap queryMap: Map<String, Any>
    ): Response
}
