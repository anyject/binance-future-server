package com.anyject.binancefutureserver.infrastructure

import com.anyject.binancefutureserver.infrastructure.client.future.BinanceFutureFeignClient
import com.anyject.binancefutureserver.infrastructure.client.response.MarketPriceResponse
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BinanceFutureFeignClientTest(
    @Autowired val feignClient: BinanceFutureFeignClient
) {
    @Test
    fun `getAllExchangeInfo 테스트`() {
        val response = feignClient.getAllExchangeInfo()
        println(">>>>>>>>>>>>>>>>" + response.body)
        val body = response.body as String

        var depth = 0
        for ( c in body ) {
            when (c) {
                '[' -> print(c)
            }
            println(c)
        }
        println(">>>>>>>>>>>>>>>>" + body)

        assertThat(response.statusCode).isEqualTo(HttpStatus.OK)
    }

    @Test
    fun `getAllMarketPrice 테스트`() {
        //val headers =     mapOf(HttpHeaders.CONTENT_TYPE to MediaType.APPLICATION_JSON.toString())
        //val response = feignClient.getAllMarketPrice(headers)
        val response = feignClient.getAllMarketPrice()
        val body = response.body as List<MarketPriceResponse>

        assertThat(response.statusCode)
            .isEqualTo(HttpStatusCode.valueOf(200))
        assertThat(body).isNotNull
    }
    @Test
    fun `getMarketPriceBySymbol 테스트`() {
        try {
            var symbol = "BTCUSDT"
            var response = feignClient.getMarketPriceBySymbol(symbol = symbol)
            var marketPrice = response.body as MarketPriceResponse
            assertThat(response.statusCode)
                .isEqualTo(HttpStatusCode.valueOf(200))
            assertThat(marketPrice).isNotNull

            symbol = "ABCDEFU"
            /* 정훈아 이건 어떻게 테스트해??? */
            response = feignClient.getMarketPriceBySymbol(symbol = symbol) // FeignClient Exception
            println(">>>>>>>>>>>>>>>>>>" + response.toString())
        }
        catch(e: Exception) {
            print(11111111111111)
        }

        //marketPrice = response.body as MarketPriceResponse
        //assertThat(response.statusCode).isEqualTo(HttpStatusCode.valueOf(400))
    }
}