package com.anyject.binancefutureserver.infrastructure.client.future.rest.trade.response

data class Order(
    val clientOrderId: String,
    val cumQty: String,
    val cumQuote: String,
    val executedQty: String,
    val orderId: Long,
    val avgPrice: String,
    val origQty: String,
    val price: String,
    val reduceOnly: Boolean,
    val side: String,
    val positionSide: String,
    val status: String,
    val stopPrice: String,
    val closePosition: Boolean,
    val symbol: String,
    val timeInForce: String,
    val type: String,
    val origType: String,
    val activatePrice: String,
    val priceRate: String,
    val updateTime: Long,
    val workingType: String,
    val priceProtect: Boolean
)
