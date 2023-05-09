package com.anyject.binancefutureserver.utils

import java.net.URLEncoder
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec


class Signatures {
    companion object{
        private val HASH_ALGORITHM = "HmacSHA256"
        @JvmStatic
        fun get(params: LinkedHashMap<String, String>, secretKey: String): String {
            val paramMap = LinkedHashMap<String, String>()

            params.forEach { paramMap[it.key] = if (it.key == "Signature" || it.value.isNullOrBlank()) "" else it.value }

            val queryString = paramMap.map { "${URLEncoder.encode(it.key, Charsets.UTF_8)}=${it.value}" }.joinToString("&")
            val hmacKey = SecretKeySpec(secretKey.toByteArray(Charsets.UTF_8), HASH_ALGORITHM)
            val hmac = Mac.getInstance(HASH_ALGORITHM)
            hmac.init(hmacKey)
            return hmac.doFinal(queryString.toByteArray(Charsets.UTF_8)).joinToString("") { "%02x".format(it) }
        }
    }
}