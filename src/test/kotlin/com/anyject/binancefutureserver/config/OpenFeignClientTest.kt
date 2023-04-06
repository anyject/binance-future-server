package com.anyject.binancefutureserver.config

import feign.*
import feign.jackson.JacksonDecoder
import feign.jackson.JacksonEncoder
import feign.okhttp.OkHttpClient
import org.apache.commons.codec.binary.Hex
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.HttpStatus
import java.nio.charset.Charset
import java.sql.Timestamp
import java.util.*
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

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
        val signature = Hmac.digest(key = secretKey, msg = totalParam)
        val queryMap = LinkedHashMap<String, Any>()
        println(signature)
        println(timestamp)
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
@EnableFeignClients
class OpenFeignConfig {
    val apiV1Url = "https://testnet.binancefuture.com/fapi/v1"
    val apiV2Url = "https://testnet.binancefuture.com/fapi/v2"
    fun getV1Client(): BinanceFutureFeignClient {
        return Feign.builder()
            .client(OkHttpClient())
            .encoder(JacksonEncoder())
            .decoder(JacksonDecoder())
            .target(BinanceFutureFeignClient::class.java, apiV1Url)
    }
    fun getV2Client(): BinanceFutureFeignClient {
        return Feign.builder()
            .client(OkHttpClient())
            .encoder(JacksonEncoder())
            .decoder(JacksonDecoder())
            .target(BinanceFutureFeignClient::class.java, apiV2Url)
    }
}

data class Test(
    val code: Int?,
    val msg: String?,
)
@FeignClient(name = "BinanceFutureFeignClient")
interface BinanceFutureFeignClient {
    @RequestLine("GET /ping")
    fun checkConnectionToServer(): Response

    @RequestLine("GET /klines")
    fun retrieveKline(
        @QueryMap queryMap: Map<String, Any>,
    ): Response

    @RequestLine("GET /account")
    fun retrieveAccountInformation(
        @HeaderMap headerMap: Map<String, String>,
        @QueryMap queryMap: Map<String, Any>,
    ): Response
}

object Hmac {
    fun digest(
        msg: String,
        key: String,
        alg: String = "HmacSHA256",
    ): String {
        val signingKey = SecretKeySpec(key.toByteArray(Charset.forName("UTF-8")), alg)
        val mac = Mac.getInstance(alg)
        mac.init(signingKey)

        val bytes = mac.doFinal(msg.toByteArray(Charset.forName("UTF-8")))
        return Hex.encodeHexString(bytes)
    }
}