package com.anyject.binancefutureserver.utils

import com.anyject.binancefutureserver.config.ApplicationProperties
import org.springframework.stereotype.Component
import java.net.URLEncoder
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

@Component
class Signatures(
    val applicationProperties: ApplicationProperties
) {
    private val HASH_ALGORITHM = "HmacSHA256"

    fun queryStringToSignature(string: String): String {
        val secretKey = applicationProperties.binance.secretKey
        val hmacKey = SecretKeySpec(secretKey.toByteArray(Charsets.UTF_8), HASH_ALGORITHM)
        val hmac = Mac.getInstance(HASH_ALGORITHM)
        hmac.init(hmacKey)
        return hmac.doFinal(string.toByteArray(Charsets.UTF_8)).joinToString("") { "%02x".format(it) }
    }
    fun getSignatureByLinkedMap(params: LinkedHashMap<String, String>): String {
        val paramMap = LinkedHashMap<String, String>()
        val secretKey = applicationProperties.binance.secretKey
        params.forEach { paramMap[it.key] = if (it.key == "Signature" || it.value.isNullOrBlank()) "" else it.value }

        val queryString = paramMap.map { "${URLEncoder.encode(it.key, Charsets.UTF_8)}=${it.value}" }.joinToString("&")
        return this.queryStringToSignature(queryString)
    }

    fun getSignatureByLinkedMap(params: LinkedHashMap<String, String>, secretKey: String): String {
        val paramMap = LinkedHashMap<String, String>()
        params.forEach { paramMap[it.key] = if (it.key == "Signature" || it.value.isNullOrBlank()) "" else it.value }

        val queryString = paramMap.map { "${URLEncoder.encode(it.key, Charsets.UTF_8)}=${it.value}" }.joinToString("&")
        return this.queryStringToSignature(queryString)
    }
}