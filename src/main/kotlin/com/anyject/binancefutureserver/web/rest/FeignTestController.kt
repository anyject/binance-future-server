package com.anyject.binancefutureserver.web.rest

import com.anyject.binancefutureserver.config.BinanceFeignClient
import com.anyject.binancefutureserver.config.TestApplicationProperties
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class FeignTestController(
    private val binanceFeignClient: BinanceFeignClient,
    private val testApplicationProperties: TestApplicationProperties

) {

    @GetMapping("/resource")
    fun getResource(): ResponseEntity<HashMap<String, Any>> {
        val headers = mapOf(HttpHeaders.CONTENT_TYPE to MediaType.APPLICATION_JSON.toString())
        val params = mapOf("symbol" to "BTCUSDT")
        return binanceFeignClient.getResource(headers, params )
    }
    @GetMapping("/")
    fun test(): String {
        println(">>>>>>>>>>>>>>>>>>>>${testApplicationProperties.apiKey}")
        println(">>>>>>>>>>>>>>>>>>>>${testApplicationProperties.secretKey}")
        println(">>>>>>>>>>>>>>>>>>>>${testApplicationProperties.defaultUrls.rest.coinm}")
        println(">>>>>>>>>>>>>>>>>>>>${testApplicationProperties.defaultUrls.rest.usdm}")
        println(">>>>>>>>>>>>>>>>>>>>${testApplicationProperties.defaultUrls.websocket.coinm}")
        println(">>>>>>>>>>>>>>>>>>>>${testApplicationProperties.defaultUrls.websocket.usdm}")
        println(">>>>>>>>>>>>>>>>>>>>${testApplicationProperties.defaultUrls.websocket.websocketApi}")
        return "!"
    }
}