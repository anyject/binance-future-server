package com.anyject.binancefutureserver.infrastructure.client.future.rest.market.response

data class MarketPriceResponse(
    val symbol: String,
    val markPrice: String,
    val indexPrice: String,
    val estimatedSettlePrice: String,
    val lastFundingRate: String,
    val nextFundingTime: Long,
    val interestRate: String,
    val time: Long
)