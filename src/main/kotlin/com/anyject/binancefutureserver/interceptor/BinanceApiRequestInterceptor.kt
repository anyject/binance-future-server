package com.anyject.binancefutureserver.interceptor

import com.anyject.binancefutureserver.config.ApplicationProperties
import feign.RequestInterceptor
import feign.RequestTemplate
import org.apache.commons.codec.binary.Hex
import org.springframework.http.HttpHeaders
import java.nio.charset.Charset
import java.security.InvalidKeyException
import java.security.NoSuchAlgorithmException
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

class BinanceApiRequestInterceptor(private val applicationProperties: ApplicationProperties) : RequestInterceptor {

    override fun apply(requestTemplate: RequestTemplate) {
        val queryString = requestTemplate.queryLine() ?: ""
        val timestamp = System.currentTimeMillis()

        val signature = createSignature(queryString, timestamp)

        requestTemplate.header(HttpHeaders.CONTENT_TYPE, "application/json")
        requestTemplate.header("X-MBX-APIKEY", applicationProperties.apiKey)
        requestTemplate.header("X-MBX-TIMESTAMP", timestamp.toString())
        requestTemplate.header("X-MBX-SIGNATURE", signature)
    }

    @Throws(NoSuchAlgorithmException::class, InvalidKeyException::class)
    private fun createSignature(queryString: String, timestamp: Long): String {
        val secret = applicationProperties.secretKey.toByteArray(Charset.forName("UTF-8"))
        val hmacSha256 = Mac.getInstance("HmacSHA256")
        hmacSha256.init(SecretKeySpec(secret, "HmacSHA256"))

        val payload = "$queryString&timestamp=$timestamp"
        val signatureBytes = hmacSha256.doFinal(payload.toByteArray(Charset.forName("UTF-8")))

        return Hex.encodeHexString(signatureBytes)
    }
}

