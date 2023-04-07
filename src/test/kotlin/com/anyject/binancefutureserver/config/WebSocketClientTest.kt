package com.anyject.binancefutureserver.config

import com.anyject.binancefutureserver.config.utils.BinanceFutureFeignClient
import com.anyject.binancefutureserver.config.utils.OpenFeignConfig
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class WebSocketClientTest {

    companion object {
        private lateinit var listenKey: String
        private lateinit var v1Client: BinanceFutureFeignClient

        @JvmStatic
        @BeforeAll
        fun getListenKey() {
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
        println(listenKey)
    }
}