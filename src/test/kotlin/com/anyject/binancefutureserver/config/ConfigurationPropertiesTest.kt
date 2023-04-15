package com.anyject.binancefutureserver.config

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class ConfigurationPropertiesTest {
    @Autowired
    lateinit var applicationProperties: ApplicationProperties

    @Test
    fun `ApplicationProperties 최상단 프로퍼티 불러오기 테스트`() {
        assertThat(applicationProperties.binance.apiKey).isNotEmpty
        assertThat(applicationProperties.binance.apiKey).isInstanceOf(String::class.java)
    }

    @Test
    fun `ApplicationProperties 객체 프로퍼티 불러오기 테스트`() {
        assertThat(applicationProperties.binance.defaultUrls).isNotNull
        assertThat(applicationProperties.binance.defaultUrls).isInstanceOf(ApplicationProperties.Binance.DefaultUrls::class.java)
    }
}