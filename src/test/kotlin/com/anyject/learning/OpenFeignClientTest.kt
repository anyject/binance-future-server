package com.anyject.learning

import com.anyject.learning.config.OpenFeignConfig
import com.anyject.learning.utils.HmacUtil
import feign.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.http.HttpStatus
import java.sql.Timestamp
import java.util.*

@ExtendWith(MockitoExtension::class)
class OpenFeignClientTest {
    private lateinit var v1Client: BinanceFutureFeignClient
    private lateinit var v2Client: BinanceFutureFeignClient

    @BeforeEach
    fun init() {
        val openFeignConfig = OpenFeignConfig()
        v1Client = openFeignConfig.getV1Client();
        v2Client = openFeignConfig.getV2Client();
    }

    @Test
    fun `OpenFeignClient Basic Connection Test`() {
        assertThat(v1Client.checkConnectionToServer().status()).isEqualTo(HttpStatus.OK.value())
    }

    @Test
    fun `Send klines request and retrieve response body`() {
        val queryMap = HashMap<String, Any>()
        queryMap["symbol"] = "BTCUSDT"
        queryMap["interval"] = "5m"
        queryMap["limit"] = 750
        val response = v1Client.retrieveKline(queryMap)
        assertThat(response.status()).isEqualTo(HttpStatus.OK.value())
        assertThat(response.body()).isNotNull
    }

    @Test
    fun `Send Account Information and Signature request and retrieve response body`() {
        val timestamp = Timestamp(System.currentTimeMillis()).time
        val secretKey = System.getenv("SECRET_KEY")
        val totalParam = "timestamp=$timestamp&recvWindow=60000"
        val signature = HmacUtil.digest(key = secretKey, msg = totalParam)
        val queryMap = LinkedHashMap<String, Any>()
        queryMap["timestamp"] = timestamp
        queryMap["recvWindow"] = 60000
        queryMap["signature"] = signature
        val headerMap = HashMap<String, String>()
        headerMap["X-MBX-APIKEY"] = System.getenv("API_KEY")
        headerMap["Content-Type"] = "application/json"
        val response = v2Client.retrieveAccountInformation(headerMap,queryMap)
        assertThat(response.status()).isEqualTo(HttpStatus.OK.value())
        assertThat(response.body()).isNotNull
    }
}