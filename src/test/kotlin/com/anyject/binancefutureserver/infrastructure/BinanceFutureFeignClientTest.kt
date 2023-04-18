package com.anyject.binancefutureserver.infrastructure

import com.anyject.binancefutureserver.infrastructure.client.future.rest.MarketDataRestFeignClient
import com.anyject.binancefutureserver.infrastructure.client.response.ExchangeInfoResponse
import com.anyject.binancefutureserver.infrastructure.client.response.MarketPriceResponse
import com.anyject.binancefutureserver.infrastructure.client.response.OrderBook
import com.anyject.binancefutureserver.infrastructure.client.response.TickerPrice24HourStatistics
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpStatusCode

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BinanceFutureFeignClientTest(
    @Autowired val feignClient: MarketDataRestFeignClient
) {

    @Test
    fun `getPing() binance api 서버 생사 확인`() {
        val response = feignClient.getPing()
        assertThat(response.statusCode).isEqualTo(HttpStatusCode.valueOf(200))
    }

    @Test
    fun `getBinanceServerTime() 서버 시간 확인`() {
        val response = feignClient.getBinanceServerTime()
        val body = response.body as String

        assertThat(response.statusCode).isEqualTo(HttpStatusCode.valueOf(200))
        assertThat(body).isNotNull
    }

    @Test
    fun `getAllTicketPrice24HourStatistics() 테스트`() {
        val response = feignClient.getAllTicketPrice24HourStatistics()
        val body = response.body as List<TickerPrice24HourStatistics>

        assertThat(response.statusCode).isEqualTo(HttpStatusCode.valueOf(200))
        assertThat(body).isNotEmpty
    }
    @Test
    fun `getTicketPrice24HourStatisticsBySymbol() 테스트`() {
        var symbol = "BTCUSDT"
        var response = feignClient.getTicketPrice24HourStatisticsBySymbol(symbol = symbol)
        var body = response.body as TickerPrice24HourStatistics
        assertThat(response.statusCode).isEqualTo(HttpStatusCode.valueOf(200))
        assertThat(body.symbol).isEqualTo(symbol)

        symbol = "ETHUSDT"
        response = feignClient.getTicketPrice24HourStatisticsBySymbol(symbol = symbol)
        body = response.body as TickerPrice24HourStatistics
        assertThat(response.statusCode).isEqualTo(HttpStatusCode.valueOf(200))
        assertThat(body.symbol).isEqualTo(symbol)
    }

    @Test
    fun `getAllExchangeInfo() 테스트`() {
        val response = feignClient.getAllExchangeInfo()
        val body = response.body as ExchangeInfoResponse

        assertThat(response.statusCode).isEqualTo(HttpStatusCode.valueOf(200))
    }

    @Test
    fun `getOrderBookBySymbol() 테스트`() {
        var symbol = "BTCUSDT"
        var limit = 5
        var response = feignClient.getOrderBookBySymbol(symbol = symbol, limit = limit)
        var body = response.body as OrderBook
        assertThat(response.statusCode).isEqualTo(HttpStatusCode.valueOf(200))
        assertThat(body).isNotNull

        symbol = "ETHUSDT"
        response = feignClient.getOrderBookBySymbol(symbol = symbol, limit = limit)
        body = response.body as OrderBook
        assertThat(response.statusCode).isEqualTo(HttpStatusCode.valueOf(200))
        assertThat(body).isNotNull
    }
    @Test
    fun `getAllMarketPrice() 테스트`() {
        //val headers =     mapOf(HttpHeaders.CONTENT_TYPE to MediaType.APPLICATION_JSON.toString())
        //val response = feignClient.getAllMarketPrice(headers)
        val response = feignClient.getAllMarketPrice()
        val body = response.body as List<MarketPriceResponse>

        assertThat(response.statusCode)
            .isEqualTo(HttpStatusCode.valueOf(200))
        assertThat(body).isNotNull
    }
    @Test
    fun `getMarketPriceBySymbol() 성공 테스트`() {
        var symbol = "BTCUSDT"
        var response = feignClient.getMarketPriceBySymbol(symbol = symbol)
        var marketPrice = response.body as MarketPriceResponse
        assertThat(response.statusCode)
            .isEqualTo(HttpStatusCode.valueOf(200))
        assertThat(marketPrice).isNotNull

        symbol = "ETHUSDT"
        response = feignClient.getMarketPriceBySymbol(symbol = symbol)
        marketPrice = response.body as MarketPriceResponse
        assertThat(response.statusCode)
            .isEqualTo(HttpStatusCode.valueOf(200))
        assertThat(marketPrice).isNotNull

    }

    @Test
    fun `getMarketPriceBySymbol() 취급하지 않은 symbol로 테스트`() {
        val symbol = "ABCDEFU"
        assertThatThrownBy {
            feignClient.getMarketPriceBySymbol(symbol = symbol) // FeignClient Exception
        }
            .isInstanceOf(Exception::class.java)
            .hasMessageContaining("Invalid symbol.")
    }

    @Test
    fun `getListKlineDataBySymbol() 테스트`() {
        var symbol = "BTCUSDT"
        var interval = "1m"
        var response = feignClient.getListKlineBySymbolAndInterval(symbol = symbol, interval = "1m")
        var body = response.body as List<List<String>>
        assertThat(response.statusCode)
            .isEqualTo(HttpStatusCode.valueOf(200))
        assertThat(body).isNotNull

        symbol = "ETHUSDT"
        response = feignClient.getListKlineBySymbolAndInterval(symbol = symbol, interval = "1m")
        body = response.body as List<List<String>>
        assertThat(response.statusCode)
            .isEqualTo(HttpStatusCode.valueOf(200))
        assertThat(body).isNotNull
    }
}