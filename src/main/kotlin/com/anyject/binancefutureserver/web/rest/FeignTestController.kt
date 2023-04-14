package com.anyject.binancefutureserver.web.rest

import com.anyject.binancefutureserver.config.ApplicationProperties
import com.anyject.binancefutureserver.config.BinanceFeignClient
import com.anyject.binancefutureserver.utils.Slf4j
import com.anyject.binancefutureserver.utils.Slf4j.Companion.log
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@Slf4j
@RestController
class FeignTestController(
    private val binanceFeignClient: BinanceFeignClient,
    private val appProperties: ApplicationProperties
) {
    @GetMapping("/resource")
    fun getResource(): Any {
        val headers = mapOf(HttpHeaders.CONTENT_TYPE to MediaType.APPLICATION_JSON.toString())
        val params = mapOf("symbol" to "BTCUSDT")
        return binanceFeignClient.getMarketPrice(headers, params)
    }
    @GetMapping("/")
    fun test(): String {
        log.info(appProperties.binance.apiKey)
        log.info(">>>>>>>>>>>>>>>>>>>>${appProperties.binance.apiKey}")
        log.info(">>>>>>>>>>>>>>>>>>>>${appProperties.binance.secretKey}")
        log.info(">>>>>>>>>>>>>>>>>>>>${appProperties.binance.defaultUrls.rest.coinm}")
        log.info(">>>>>>>>>>>>>>>>>>>>${appProperties.binance.defaultUrls.rest.usdm}")
        log.info(">>>>>>>>>>>>>>>>>>>>${appProperties.binance.defaultUrls.websocket.coinm}")
        log.info(">>>>>>>>>>>>>>>>>>>>${appProperties.binance.defaultUrls.websocket.usdm}")
        log.info(">>>>>>>>>>>>>>>>>>>>${appProperties.binance.defaultUrls.websocket.websocketApi}")
        return "!"
    }
}