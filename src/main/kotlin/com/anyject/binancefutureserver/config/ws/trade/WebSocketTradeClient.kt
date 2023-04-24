package com.anyject.binancefutureserver.config.ws.trade

import com.anyject.binancefutureserver.config.ApplicationProperties
import org.springframework.stereotype.Component
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketHandler
import org.springframework.web.socket.WebSocketHttpHeaders
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.client.standard.StandardWebSocketClient
import java.net.URI

@Component
class WebSocketTradeClient(
    val properties: ApplicationProperties,
    val handler: WebSocketHandler
) {

    private val baseUrl = "wss://testnet.binance.vision/ws-api/v3"
    private lateinit var session: WebSocketSession
    fun connect() {
        session = StandardWebSocketClient()
            .doHandshake(handler, WebSocketHttpHeaders(),  URI.create(baseUrl))
            .get()
    }

    fun getServerTime() {
        val json = """
            {
              "id": "187d3cb2-942d-484c-8271-4e2141bbadb1",
              "method": "time"
            }
        """.trimIndent()
        session.sendMessage(TextMessage(json))
    }
}