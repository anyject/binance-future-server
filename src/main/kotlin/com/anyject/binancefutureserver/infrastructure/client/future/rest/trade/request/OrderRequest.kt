package com.anyject.binancefutureserver.infrastructure.client.future.rest.trade.request

data class OrderRequest (
    val symbol: String,
    val side: String,
    val positionSide: String? = null,
    val type: String,
    val timeInForce: String? = null,
    val quantity: Double? = null,
    val reduceOnly: String? = null,
    val price: Double? = null,
    val newClientOrderId: String? = null,
    val stopPrice: Double? = null,
    val closePosition: String? = null,
    val activationPrice: Double? = null,
    val callbackRate: Double? = null,
    val workingType: String? = null,
    val priceProtect: String? = null,
    val newOrderRespType: String? = null,
    val recvWindow: Long? = null,
    val timestamp: Long
)