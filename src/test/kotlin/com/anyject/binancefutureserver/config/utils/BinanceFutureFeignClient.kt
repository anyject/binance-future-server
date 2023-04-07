package com.anyject.binancefutureserver.config.utils

import feign.HeaderMap
import feign.QueryMap
import feign.RequestLine
import feign.Response
import org.springframework.cloud.openfeign.FeignClient

@FeignClient(name = "BinanceFutureFeignClient")
interface BinanceFutureFeignClient {
    @RequestLine("GET /ping")
    fun checkConnectionToServer(): Response

    @RequestLine("GET /klines")
    fun retrieveKline(
        @QueryMap queryMap: Map<String, Any>,
    ): Response

    @RequestLine("GET /account")
    fun retrieveAccountInformation(
        @HeaderMap headerMap: Map<String, String>,
        @QueryMap queryMap: Map<String, Any>,
    ): Response
}