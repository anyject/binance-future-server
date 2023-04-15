package com.anyject.learning.config

import com.anyject.binancefutureserver.BinanceFutureServerApplication
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.core.env.Environment
import org.springframework.core.env.get
import org.springframework.test.context.ActiveProfiles

@SpringBootTest(classes = [BinanceFutureServerApplication::class])
class EnvironmentTest {
    @Autowired
    lateinit var env: Environment

    @Test
    fun `Get application-yml data`() {
        assertThat(env["app.telegram.username"]).isInstanceOf(String::class.java)
    }

    @Test
    fun `Get application-testnet data`() {
        assertThat(env["app.binance.api-key"]).isInstanceOf(String::class.java)
        assertThat(env["app.binance.default-urls.rest.usdm"]).isInstanceOf(String::class.java)
        assertThat(env["app.binance.default-urls.websocket.usdm"]).isInstanceOf(String::class.java)
    }
}