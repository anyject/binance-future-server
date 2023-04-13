package com.anyject.learning.config

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.core.env.Environment
import org.springframework.core.env.get
import org.springframework.test.context.ActiveProfiles

@SpringBootTest
@ActiveProfiles("testnet")
class EnvironmentTest {
    @Autowired
    lateinit var env: Environment

    @Test
    fun `Get application-yml data`() {
        assertThat(env["telegram.username"]).isInstanceOf(String::class.java)
    }

    @Test
    fun `Get application-testnet data`() {
        assertThat(env["binance.api-key"]).isInstanceOf(String::class.java)
        assertThat(env["binance.default-urls.rest.usdm"]).isInstanceOf(String::class.java)
        assertThat(env["binance.default-urls.websocket.usdm"]).isInstanceOf(String::class.java)
    }
}