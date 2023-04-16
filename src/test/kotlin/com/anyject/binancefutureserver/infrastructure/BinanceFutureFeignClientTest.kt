package com.anyject.binancefutureserver.infrastructure

import com.anyject.binancefutureserver.infrastructure.client.future.BinanceFutureFeignClient
import com.anyject.binancefutureserver.infrastructure.client.response.ExchangeInfoResponse
import com.anyject.binancefutureserver.infrastructure.client.response.MarketPriceResponse
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpStatusCode

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BinanceFutureFeignClientTest(
    @Autowired val feignClient: BinanceFutureFeignClient
) {
    @Test
    fun `getAllExchangeInfo 테스트`() {
        val response = feignClient.getAllExchangeInfo()
        val body = response.body as ExchangeInfoResponse

        assertThat(response.statusCode).isEqualTo(HttpStatusCode.valueOf(200))
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
    fun `getMarketPriceBySymbol 성공 테스트`() {
        var symbol = "BTCUSDT"
        var response = feignClient.getMarketPriceBySymbol(symbol = symbol)
        var marketPrice = response.body as MarketPriceResponse
        assertThat(response.statusCode)
            .isEqualTo(HttpStatusCode.valueOf(200))
        assertThat(marketPrice).isNotNull

    }

    @Test
    fun `getMarketPriceBySymbol 취급하지 않은 symbol로 테스트`() {
        val symbol = "ABCDEFU"
        assertThatThrownBy {
            feignClient.getMarketPriceBySymbol(symbol = symbol) // FeignClient Exception
        }
            .isInstanceOf(Exception::class.java)
            .hasMessageContaining("Invalid symbol.")
    }

}