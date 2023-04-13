package com.anyject.binancefutureserver.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix = "binance")
class TestApplicationProperties {
    var apiKey: String? = null
<<<<<<< Updated upstream:src/main/kotlin/com/anyject/binancefutureserver/config/TestApplicationProperties.kt
    var secretKey: String? = null
    var defaultUrls: DefaultUrls = DefaultUrls()
=======
    var secretKey: String? = nul
    var defaultUrls: DefaultUrls? = null
>>>>>>> Stashed changes:src/main/kotlin/com/anyject/binancefutureserver/config/ApplicationProperties.kt

    class DefaultUrls {
        var rest: RestUrls = RestUrls()
        var websocket: WebsocketUrls = WebsocketUrls()

        class RestUrls {
            var usdm: String? = null
            var coinm: String? = null
        }

        class WebsocketUrls {
            var usdm: String? = null
            var coinm: String? = null
            var websocketApi: String? = null
        }
    }
}