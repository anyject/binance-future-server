package com.anyject.learning.config

import com.anyject.learning.BinanceFutureFeignClient
import feign.Feign
import feign.jackson.JacksonDecoder
import feign.jackson.JacksonEncoder
import feign.okhttp.OkHttpClient
import org.springframework.cloud.openfeign.EnableFeignClients

@EnableFeignClients
class OpenFeignConfig {
    val apiV1Url = "https://testnet.binancefuture.com/fapi/v1"
    val apiV2Url = "https://testnet.binancefuture.com/fapi/v2"
    fun getV1Client(): BinanceFutureFeignClient {
        return Feign.builder()
            .client(OkHttpClient())
            .encoder(JacksonEncoder())
            .decoder(JacksonDecoder())
            .target(BinanceFutureFeignClient::class.java, apiV1Url)
    }
    fun getV2Client(): BinanceFutureFeignClient {
        return Feign.builder()
            .client(OkHttpClient())
            .encoder(JacksonEncoder())
            .decoder(JacksonDecoder())
            .target(BinanceFutureFeignClient::class.java, apiV2Url)
    }
}