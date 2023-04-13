package com.anyject.binancefutureserver.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix = "binance")
class ApplicationProperties {
    lateinit var apiKey: String
    lateinit var secretKey: String
    val defaultUrls = DefaultUrls()

    class DefaultUrls {
        val rest = Rest()
        val websocket = Websocket()

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