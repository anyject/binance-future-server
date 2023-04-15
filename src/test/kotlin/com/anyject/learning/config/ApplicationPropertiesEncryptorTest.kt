package com.anyject.learning.config

import com.anyject.binancefutureserver.BinanceFutureServerApplication
import com.anyject.binancefutureserver.config.ApplicationPropertiesEncryptorConfig
import org.assertj.core.api.Assertions.assertThat
import org.jasypt.encryption.StringEncryptor
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(classes = [BinanceFutureServerApplication::class,
    ApplicationPropertiesEncryptorConfig::class,
    ApplicationPropertiesPrinter::class])
@Disabled
class ApplicationPropertiesEncryptorTest {
    @Autowired
    private lateinit var jasyptStringEncryptor: StringEncryptor

    @Autowired
    private lateinit var applicationPropertiesPrinter: ApplicationPropertiesPrinter

    @Test
    fun `Get Encrypted Properties`() {
        val map = applicationPropertiesPrinter.printMap()
        map.forEach { (key, value) ->
            val encrypted = jasyptStringEncryptor.encrypt(value)
            val decrypted = jasyptStringEncryptor.decrypt(encrypted)
            println("$key : $encrypted, decrypted value : $decrypted")
            assertThat(value).isEqualTo(decrypted)
        }
    }
}
