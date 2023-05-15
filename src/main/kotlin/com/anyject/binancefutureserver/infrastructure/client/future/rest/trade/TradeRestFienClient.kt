package com.anyject.binancefutureserver.infrastructure.client.future.rest.trade

import com.anyject.binancefutureserver.infrastructure.client.future.rest.common.CommonHeader
import com.anyject.binancefutureserver.infrastructure.client.future.rest.trade.response.OrderResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestHeader


@FeignClient(name = "binance", url = "\${app.binance.default-urls.rest.usdm}")
interface TradeRestFienClient {
    @PostMapping("/fapi/v1/order")
    fun newOrder(
        @RequestHeader headers: CommonHeader? = CommonHeader()
    ): ResponseEntity<OrderResponse>
}