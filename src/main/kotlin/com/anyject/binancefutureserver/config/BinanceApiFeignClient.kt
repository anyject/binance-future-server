package com.anyject.binancefutureserver.config

import com.anyject.binancefutureserver.web.rest.ApiController
import feign.Headers
import feign.Param
import feign.RequestLine

interface BinanceApiFeignClient {

    @RequestLine("GET /fapi/v1/exchangeInfo")
    @Headers("Content-Type: application/json", "X-MBX-APIKEY: {apiKey}")
    fun getExchangeInfo(@Param("apiKey") apiKey: String): ApiController.BinanceExchangeInfo
    fun getPrice(symbol: String): ApiController.BinanceTickerPrice
}

data class ExchangeInfo(val symbols: List<Symbol>)

data class Symbol(val symbol: String, val status: String)