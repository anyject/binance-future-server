package com.anyject.binancefutureserver.infrastructure.client.response

data class ExchangeInfoResponse(
    val exchangeFilters: List<Any>?,
    val rateLimits: List<RateLimit>?,
    val serverTime: String?,
    val assets: List<Asset>?,
    val symbols: List<Symbol>?,
    val timezone: String?
) {
    data class RateLimit(
        val interval: String?,
        val intervalNum: String?,
        val limit: String?,
        val rateLimitType: String?
    )

    data class Asset(
        val asset: String?,
        val marginAvailable: String?,
        val autoAssetExchange: String?
    )

    data class Symbol(
        val symbol: String?,
        val pair: String?,
        val contractType: String?,
        val deliveryDate: String?,
        val onboardDate: String?,
        val status: String?,
        val maintMarginPercent: String?,
        val requiredMarginPercent: String?,
        val baseAsset: String?,
        val quoteAsset: String?,
        val marginAsset: String?,
        val pricePrecision: String?,
        val quantityPrecision: String?,
        val baseAssetPrecision: String?,
        val quotePrecision: String?,
        val underlyingType: String?,
        val underlyingSubType: List<String>?,
        val settlePlan: String?,
        val triggerProtect: String?,
        val liquidationFee: String?,
        val marketTakeBound: String?,
        val maxMoveOrderLimit: String?,
        val filters: List<Filter>?,
        val orderTypes: List<String>?,
        val timeInForce: List<String>?
    )

    data class Filter(
        val filterType: String?,
        val minPrice: String?,
        val maxPrice: String?,
        val tickSize: String?,
        val stepSize: String?,
        val maxQty: String?,
        val minQty: String?,
        val multiplierUp: String?,
        val multiplierDown: String?,
        val multiplierDecimal: String?,
        val notional: String?,
        val limit: String?
    )
}