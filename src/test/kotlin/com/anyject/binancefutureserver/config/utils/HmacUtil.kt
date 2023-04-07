package com.anyject.binancefutureserver.config.utils

import org.apache.commons.codec.binary.Hex
import java.nio.charset.Charset
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

object HmacUtil {
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