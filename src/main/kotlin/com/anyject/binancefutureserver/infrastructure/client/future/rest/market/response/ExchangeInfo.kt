package com.anyject.binancefutureserver.infrastructure.client.future.rest.market.response

data class ExchangeInfo(
    val exchangeFilters: List<Any>,
    val rateLimits: List<RateLimit>,
    val serverTime: Long,
    val assets: List<Asset>,
    val symbols: List<Symbol>,
    val timezone: String
) {
    data class RateLimit(
        val interval: String,
        val intervalNum: Int,
        val limit: Int,
        val rateLimitType: String
    )

    data class Asset(
        val asset: String,
        val marginAvailable: Boolean,
        val autoAssetExchange: Double?
    )

    data class Symbol(
        val symbol: String,
        val pair: String,
        val contractType: String,
        val deliveryDate: Long,
        val onboardDate: Long,
        val status: String,
        val maintMarginPercent: String,
        val requiredMarginPercent: String,
        val baseAsset: String,
        val quoteAsset: String,
        val marginAsset: String,
        val pricePrecision: Int,
        val quantityPrecision: Int,
        val baseAssetPrecision: Int,
        val quotePrecision: Int,
        val underlyingType: String,
        val underlyingSubType: List<String>,
        val settlePlan: Long,
        val triggerProtect: String,
        val liquidationFee: String,
        val marketTakeBound: String,
        val maxMoveOrderLimit: Int,
        val filters: List<Filter>,
        val orderTypes: List<String>,
        val timeInForce: List<String>
    )

    data class Filter(
        val minPrice: String? = null,
        val maxPrice: String? = null,
        val filterType: String,
        val tickSize: String? = null,
        val stepSize: String? = null,
        val maxQty: String? = null,
        val minQty: String? = null,
        val limit: Int? = null,
        val notional: String? = null,
        val multiplierDown: String? = null,
        val multiplierUp: String? = null,
        val multiplierDecimal: String? = null
    )
}
