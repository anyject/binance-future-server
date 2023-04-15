package com.anyject.binancefutureserver.web.rest

import com.anyject.binancefutureserver.config.ApplicationProperties
import com.anyject.binancefutureserver.config.BinanceFeignClient
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class FeignTestController(
    private val binanceFeignClient: BinanceFeignClient,
    private val appProperties: ApplicationProperties
) {

    @GetMapping("/resource")
    fun getResource(): ResponseEntity<HashMap<String, Any>> {
        val headers = mapOf(HttpHeaders.CONTENT_TYPE to MediaType.APPLICATION_JSON.toString())
        val params = mapOf("symbol" to "BTCUSDT")
        return binanceFeignClient.premiumIndex(headers, params)
    }
    @GetMapping("/")
    fun test(): String {
        println(">>>>>>>>>>>>>>>>>>>>${appProperties.binance.apiKey}")
        println(">>>>>>>>>>>>>>>>>>>>${appProperties.binance.secretKey}")
        println(">>>>>>>>>>>>>>>>>>>>${appProperties.binance.defaultUrls!!.rest!!.coinm}")
        println(">>>>>>>>>>>>>>>>>>>>${appProperties.binance.defaultUrls!!.rest!!.usdm}")
        println(">>>>>>>>>>>>>>>>>>>>${appProperties.binance.defaultUrls!!.websocket!!.coinm}")
        println(">>>>>>>>>>>>>>>>>>>>${appProperties.binance.defaultUrls!!.websocket!!.usdm}")
        println(">>>>>>>>>>>>>>>>>>>>${appProperties.binance.defaultUrls!!.websocket!!.websocketApi}")
        return "!"
    }
}