package com.anyject.binancefutureserver.config

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties
import org.jasypt.encryption.StringEncryptor
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment
import org.springframework.core.env.get

@Configuration
@EnableEncryptableProperties
class ApplicationPropertiesEncryptorConfig(val environment: Environment) {
    @Bean
    fun jasyptStringEncryptor(): StringEncryptor {
        val encryptPassword = environment["app.jasypt.encryptor.password"]
        val encryptAlgorithm = Constants.JASYPT_ENCRYPT_ALGORITHM

        val encryptor = PooledPBEStringEncryptor()

        val config = SimpleStringPBEConfig()
        config.password = encryptPassword
        config.algorithm = encryptAlgorithm
        config.poolSize = 1
        config.keyObtentionIterations = 1000
        config.stringOutputType = "base64"
        config.providerName = "SunJCE"
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator")
        config.setIvGeneratorClassName("org.jasypt.iv.NoIvGenerator")
        encryptor.setConfig(config)
        return encryptor
    }
}