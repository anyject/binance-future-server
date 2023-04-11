package com.anyject.binancefutureserver.web.rest

import com.anyject.binancefutureserver.config.BinanceApiFeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class ApiController(private val binanceApiFeignClient: BinanceApiFeignClient) {

    data class BinanceSymbolFilter(
        val filterType: String,
        val minPrice: String? = null,
        val maxPrice: String? = null,
        val tickSize: String? = null,
        val minQty: String? = null,
        val maxQty: String? = null,
        val stepSize: String? = null,
        val minNotional: String? = null,
        val limit: Int? = null,
        val multiplierDown: String? = null,
        val multiplierUp: String? = null,
        val avgPriceMins: Int? = null,
        val applyToMarket: Boolean? = null,
        val limitDown: String? = null,
        val limitUp: String? = null,
        val maxNumOrders: Int? = null,
        val maxNumAlgoOrders: Int? = null,
        val maxNumIcebergOrders: Int? = null,
        val maxPosition: String? = null
    )

    data class BinanceSymbolInfo(
        val symbol: String,
        val status: String,
        val baseAsset: String,
        val baseAssetPrecision: Int,
        val quoteAsset: String,
        val quotePrecision: Int,
        val quoteAssetPrecision: Int,
        val baseCommissionPrecision: Int,
        val quoteCommissionPrecision: Int,
        val orderTypes: List<String>,
        val icebergAllowed: Boolean,
        val ocoAllowed: Boolean,
        val quoteOrderQtyMarketAllowed: Boolean,
        val isSpotTradingAllowed: Boolean,
        val isMarginTradingAllowed: Boolean,
        val filters: List<BinanceSymbolFilter>
    )

    data class BinanceRateLimit(
        val rateLimitType: String,
        val interval: String,
        val intervalNum: Int,
        val limit: Int
    )

    data class BinanceExchangeInfo(
        val timezone: String,
        val serverTime: Long,
        val rateLimits: List<BinanceRateLimit>,
        val symbols: List<BinanceSymbolInfo>
    )
    data class BinanceTickerPrice(
        val symbol: String,
        val price: String
    )
    @GetMapping("/exchangeInfo")
    fun getExchangeInfo(): BinanceExchangeInfo {
        return binanceApiFeignClient.getExchangeInfo("")
    }

    @GetMapping("/ticker/price")
    fun getPrice(@RequestParam("symbol") symbol: String): BinanceTickerPrice {
        return binanceApiFeignClient.getPrice(symbol)
    }
}