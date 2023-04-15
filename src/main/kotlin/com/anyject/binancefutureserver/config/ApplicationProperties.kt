package com.anyject.binancefutureserver.config

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "app", ignoreInvalidFields = false)
data class ApplicationProperties (
    val binance: Binance = Binance(),
    val telegram: Telegram = Telegram(),
    val jasypt: Jasypt = Jasypt()
) {
    data class Binance (
        var apiKey: String? = null,
        var secretKey: String? = null,
        var defaultUrls: DefaultUrls? = DefaultUrls()
    ){
        data class DefaultUrls (
            var rest : Rest? = Rest(),
            var websocket : Websocket? = Websocket()
        ) {

            data class Rest (
                var usdm: String? = null,
                var coinm: String? = null
            )

            data class Websocket (
                var usdm: String? = null,
                var coinm: String? = null,
                var websocketApi: String? = null
                )
        }
    }
    data class Telegram (
        var username: String? = null,
        var apiKey: String? = null
    )

    data class Jasypt (
        var encryptor :Encryptor = Encryptor()
        ) {
        data class Encryptor (
            var bean: String? = null,
            var algorithm: String? = null,
            var password: String? = null,
            var ivGeneratorClassname: String? = null,
            var property: JasyptProperty = JasyptProperty()
        ) {
            data class JasyptProperty(
                var prefix: String? = null,
                var suffix: String? = null
            )
        }
    }
}