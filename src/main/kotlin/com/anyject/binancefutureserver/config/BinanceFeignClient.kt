package com.anyject.binancefutureserver.config

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestParam


@FeignClient(name = "binance", url = "https://testnet.binancefuture.com")
interface BinanceFeignClient {

    @GetMapping("/fapi/v1/premiumIndex")
    fun getResource(@RequestHeader headers: Map<String, String>,
                    @RequestParam params: Map<String, Any>,
    ): ResponseEntity<HashMap<String, Any>>
}