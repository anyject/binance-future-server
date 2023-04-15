package com.anyject.learning

import com.anyject.binancefutureserver.config.Constants
import org.assertj.core.api.Assertions.assertThat
import org.bouncycastle.jce.provider.BouncyCastleProvider
import org.jasypt.encryption.StringEncryptor
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor
import org.jasypt.encryption.pbe.config.SimplePBEConfig
import org.jasypt.iv.RandomIvGenerator
import org.jasypt.salt.RandomSaltGenerator
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test

class JasyptTest {
    companion object {
        private lateinit var encryptor: StringEncryptor

        @JvmStatic
        fun applicationPropertiesEncryptor(): StringEncryptor {
            val encryptPassword = System.getenv("APP_ENCRYPTION_PASSWORD")
            val encryptAlgorithm = Constants.JASYPT_ENCRYPT_ALGORITHM

            val encryptor = PooledPBEStringEncryptor()

            val config = SimplePBEConfig()
            config.ivGenerator = RandomIvGenerator()
            config.saltGenerator = RandomSaltGenerator()
            config.provider = BouncyCastleProvider()
            config.password = encryptPassword
            config.algorithm = encryptAlgorithm
            config.poolSize = 1
            config.keyObtentionIterations = 1000
            encryptor.setConfig(config)
            return encryptor
        }

        @JvmStatic
        @BeforeAll
        fun init() {
            encryptor = applicationPropertiesEncryptor()
        }
    }

    @Test
    fun `Jasypt encryption and decryption test`() {
        val value = "TEST"
        val encryptedValue = encryptor.encrypt(value)
        val decryptedValue = encryptor.decrypt(encryptedValue)
        assertThat(value).isEqualTo(decryptedValue)
        println(encryptedValue)
    }
}