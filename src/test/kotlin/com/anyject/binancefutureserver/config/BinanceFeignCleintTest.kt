package com.anyject.binancefutureserver.config

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BinanceFeignCleintTest(
    @Autowired val feignClient: BinanceFeignClient
) {

    @Test
    fun `premiumIndex 테스트`() {
        val headers = mapOf(HttpHeaders.CONTENT_TYPE to MediaType.APPLICATION_JSON.toString())
        //val params = mapOf("symbol" to "BTCUSDT")
        val params = HashMap<String, Any>()
        val json = feignClient.getMarketPrice(headers, params)


        println(json.javaClass)
        Assertions.assertThat(json).isNotNull
    }
}