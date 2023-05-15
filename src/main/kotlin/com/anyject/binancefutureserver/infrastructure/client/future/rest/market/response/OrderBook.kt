package com.anyject.binancefutureserver.infrastructure.client.future.rest.market.response

data class OrderBook(
    val lastUpdateId: Long, // 호가창의 마지막 업데이트 ID
    val E: Long, // 이벤트 시간
    val T: Long, // 이벤트 유형
    val bids: List<List<String>>, // 매수 호가 리스트. (price, quantity)
    val asks: List<List<String>> // 매도 호가 리스트. (price, quantity)
)