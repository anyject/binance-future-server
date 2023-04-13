package com.anyject.learning

import com.anyject.learning.vo.ListenKeyVO
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
        @HeaderMap headerMap: Map<String, Any>,
        @QueryMap queryMap: Map<String, Any>,
    ): Response

    @RequestLine("POST /listenKey")
    fun retrieveListenKey(@HeaderMap headerMap: Map<String, String>): ListenKeyVO
}