package com.anyject.binancefutureserver.config

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration

@SpringBootTest
@ActiveProfiles("testnet")
@ContextConfiguration(classes = [ApplicationProperties::class])
@EnableConfigurationProperties(ApplicationProperties::class)
class ConfigurationPropertiesTest {
    @Autowired
    lateinit var applicationProperties: ApplicationProperties

    @Test
    fun `ApplicationProperties 최상단 프로퍼티 불러오기 테스트`() {
        assertThat(applicationProperties.apiKey).isNotEmpty
        assertThat(applicationProperties.apiKey).isInstanceOf(String::class.java)
    }

    @Test
    fun `ApplicationProperties 객체 프로퍼티 불러오기 테스트`() {
        assertThat(applicationProperties.defaultUrls).isNotNull
        assertThat(applicationProperties.defaultUrls).isInstanceOf(ApplicationProperties.DefaultUrls::class.java)
    }
}