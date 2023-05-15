package com.anyject.binancefutureserver.infrastructure.client.future.rest.market.response

data class KlineResponse (
    val openTime: Long, // 시작 시간 (timestamp)
    val open: String, // 시작 가격
    val high: String, // 최고 가격
    val low: String, // 최저 가격
    val close: String, // 종료 가격
    val volume: String, // 거래량 (base asset)
    val closeTime: Long, // 종료 시간 (timestamp)
    val quoteAssetVolume: String, // 거래대금 (quote asset)
    val numOfTrades: Int, // 거래 횟수
    val takerBuyBaseAssetVolume: String, // 매수 거래량 (base asset)
    val takerBuyQuoteAssetVolume: String, // 매수 거래대금 (quote asset)
    val ignore: String // 데이터 무시 (필요 없는 정보)
)