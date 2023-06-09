package com.anyject.binancefutureserver.infrastructure.client.future.rest

import com.anyject.binancefutureserver.infrastructure.client.request.header.CommonHeader
import com.anyject.binancefutureserver.infrastructure.client.response.ExchangeInfoResponse
import com.anyject.binancefutureserver.infrastructure.client.response.MarketPriceResponse
import com.anyject.binancefutureserver.infrastructure.client.response.OrderBook
import com.anyject.binancefutureserver.infrastructure.client.response.TickerPrice24HourStatistics
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestParam


@FeignClient(name = "binance", url = "\${app.binance.default-urls.rest.usdm}")
interface MarketDataRestFeignClient {
    @GetMapping("/fapi/v1/ping")
    fun getPing(
        @RequestHeader headers: CommonHeader? = CommonHeader()
    ): ResponseEntity<Any>

    @GetMapping("/fapi/v1/time")
    fun getBinanceServerTime(
        @RequestHeader headers: CommonHeader? = CommonHeader()
    ): ResponseEntity<String>

    @GetMapping("/fapi/v1/exchangeInfo")
    fun getAllExchangeInfo(
        @RequestHeader headers: CommonHeader? = CommonHeader()
    ): ResponseEntity<ExchangeInfoResponse>

    @GetMapping("/fapi/v1/depth")
    fun getOrderBookBySymbol(
        @RequestHeader headers: CommonHeader? = CommonHeader(),
        @RequestParam symbol: String,
        @RequestParam limit: Int? = 100 // Default 100; max 1000. Valid limits:[5, 10, 20, 50, 100, 500, 1000]
    ): ResponseEntity<OrderBook>

    @GetMapping("/fapi/v1/ticker/24hr")
    fun getAllTicketPrice24HourStatistics(
        @RequestHeader headers: CommonHeader? = CommonHeader()
    ): ResponseEntity<List<TickerPrice24HourStatistics>>

    @GetMapping("/fapi/v1/ticker/24hr")
    fun getTicketPrice24HourStatisticsBySymbol(
        @RequestHeader headers: CommonHeader? = CommonHeader(),
        @RequestParam symbol: String
    ): ResponseEntity<TickerPrice24HourStatistics>

    @GetMapping("/fapi/v1/premiumIndex")
    fun getAllMarketPrice(
        @RequestHeader headers: CommonHeader? = CommonHeader()
    ): ResponseEntity<List<MarketPriceResponse>>

    @GetMapping("/fapi/v1/premiumIndex")
    fun getMarketPriceBySymbol(
        @RequestHeader headers: CommonHeader? = CommonHeader(),
        @RequestParam symbol: String,
    ): ResponseEntity<MarketPriceResponse>

    @GetMapping("/fapi/v1/klines")
    fun getListKlineBySymbolAndInterval(
        @RequestHeader headers: CommonHeader? = CommonHeader(),
        @RequestParam symbol: String,
        @RequestParam interval: String,
    ): ResponseEntity<List<List<String>>>
}