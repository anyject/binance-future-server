package com.anyject.binancefutureserver.utils

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class SignaturesTest(
    @Autowired
    private val signatures: Signatures
) {
    @Test
    fun `get(LinkedHashMap, String) Test`() {
        val secretKey = "1fb1c87857991654d84b1fc4c554e7d5c32b65dbc5e98a687248e7a7fec7cf39"
        val params1 = linkedMapOf(
            "recvWindow" to "5000",
            "timestamp" to "1680952020246"
        )
        Assertions.assertThat(signatures.getSignatureByLinkedMap(params1, secretKey)).isEqualTo("81c863f5bd67f70560e5acd09e1a25fa315f42b787b9964416f31571cce5c489")
        "".isEmpty()

        val params2 = linkedMapOf(
            "recvWindow" to "5000",
            "timestamp" to "1680953425440"
        )
        Assertions.assertThat(signatures.getSignatureByLinkedMap(params1, secretKey)).isEqualTo("0ed50cb69b800d62a9e8142f6ffc91925f7226d9a8afb20ba4e9aefd2030e6f8")
    }
}