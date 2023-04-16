package com.anyject.binancefutureserver.infrastructure.client.future

import com.anyject.binancefutureserver.infrastructure.client.param.CommonHeader
import com.anyject.binancefutureserver.infrastructure.client.response.ExchangeInfoResponse
import com.anyject.binancefutureserver.infrastructure.client.response.MarketPriceResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestParam


@FeignClient(name = "binance", url = "\${app.binance.default-urls.rest.usdm}")
interface BinanceFutureFeignClient {

    @GetMapping("/fapi/v1/exchangeInfo")
    fun getAllExchangeInfo(
        @RequestHeader headers: CommonHeader? = CommonHeader()
    ): ResponseEntity<ExchangeInfoResponse>

    @GetMapping("/fapi/v1/premiumIndex")
    fun getAllMarketPrice(
        @RequestHeader headers: CommonHeader? = CommonHeader()
    ): ResponseEntity<List<MarketPriceResponse>>

    @GetMapping("/fapi/v1/premiumIndex")
    fun getMarketPriceBySymbol(
        @RequestHeader headers: CommonHeader? = CommonHeader(),
        @RequestParam symbol: String,
    ): ResponseEntity<MarketPriceResponse>

}