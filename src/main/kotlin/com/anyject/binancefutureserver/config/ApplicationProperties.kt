package com.anyject.binancefutureserver.config

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "app", ignoreInvalidFields = false)
data class ApplicationProperties (
    val binance: Binance = Binance(),
    val telegram: Telegram = Telegram(),
    val jasypt: Jasypt = Jasypt()
) {
    class Binance {
        lateinit var apiKey: String
        lateinit var secretKey: String
        val defaultUrls: DefaultUrls = DefaultUrls()

        data class DefaultUrls (
            val rest : Rest = Rest(),
            val websocket : Websocket = Websocket()
        ) {
            class Rest {
                lateinit var usdm: String
                lateinit var coinm: String
            }

            class Websocket {
                lateinit var usdm: String
                lateinit var coinm: String
                lateinit var websocketApi: String
            }
        }
    }
    class Telegram {
        lateinit var username: String
        lateinit var apiKey: String
    }

    data class Jasypt (
        val encryptor : Encryptor = Encryptor()
    ) {
        class Encryptor {
            lateinit var bean: String
            lateinit var algorithm: String
            lateinit var password: String
            lateinit var ivGeneratorClassname: String
            val property: JasyptProperty = JasyptProperty()
            class JasyptProperty {
                lateinit var prefix: String
                lateinit var suffix: String
            }
        }
    }
}