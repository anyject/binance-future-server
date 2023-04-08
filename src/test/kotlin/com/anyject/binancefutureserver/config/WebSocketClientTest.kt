package com.anyject.binancefutureserver.config

import com.anyject.binancefutureserver.config.utils.BinanceFutureFeignClient
import com.anyject.binancefutureserver.config.utils.OpenFeignConfig
import com.fasterxml.jackson.databind.ObjectMapper
import org.assertj.core.api.Assertions.assertThat
import org.awaitility.Awaitility.await
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.springframework.web.reactive.socket.client.ReactorNettyWebSocketClient
import org.springframework.web.reactive.socket.client.WebSocketClient
import reactor.core.Disposable
import reactor.core.publisher.Mono
import java.net.URI
import java.time.Duration
import java.util.*
import java.util.concurrent.TimeUnit

class WebSocketClientTest {

    companion object {
        private lateinit var listenKey: String
        private lateinit var v1Client: BinanceFutureFeignClient
        private lateinit var webSocketClient: WebSocketClient
        private lateinit var objectMapper: ObjectMapper

        @JvmStatic
        @BeforeAll
        fun init() {
            setListenKey()
            setWebSocketClient()
            setObjectMapper()
        }

        private fun setObjectMapper() {
            objectMapper = ObjectMapper()
        }

        private fun setWebSocketClient() {
            webSocketClient = ReactorNettyWebSocketClient()
        }

        private fun setListenKey() {
            v1Client = OpenFeignConfig().getV1Client()
            val headerMap = HashMap<String, String>()
            headerMap["X-MBX-APIKEY"] = System.getenv("API_KEY")
            headerMap["Content-Type"] = "application/json"
            listenKey = v1Client.retrieveListenKey(headerMap).listenKey
        }

    }

    @Test
    fun `WebSocket USD-M Start User Data Stream Success Test`() {
        assertThat(listenKey).isNotNull
    }

    @Test
    fun `WebSocket API Make UUID and Send Request to Test Connectivity API`() {
        val param = WebSocketParam(
            id = UUID.randomUUID().toString(),
            method = "ping"
        )
        val url = "wss://testnet.binance.vision/ws-api/v3"
        val response = webSocketClient.execute(
            URI.create(url)
        ) { session ->
            session.send(Mono.just(session.textMessage(objectMapper.writeValueAsString(param))))
                .doOnNext{
            session.receive().doOnNext {
                println("Payload : ${it.payloadAsText}")
            }
                .doOnNext{ assertThat(it.payloadAsText).isNotBlank()}
                .doOnNext{session.close()}}
        }.doFinally { println("FINISH!!") }.subscribe()
        await().atMost(5L, TimeUnit.SECONDS).untilAsserted{ assertThat(response).isInstanceOf(Disposable::class.java)}
    }

    private data class WebSocketParam(
        val id: String,
        val method: String,
    )
}