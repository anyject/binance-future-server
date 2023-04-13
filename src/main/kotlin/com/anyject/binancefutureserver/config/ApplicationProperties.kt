package com.anyject.binancefutureserver.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix = "binance")
class ApplicationProperties {
    var apiKey: String? = null
    var secretKey: String? = null
    var defaultUrls: DefaultUrls? = null

    class DefaultUrls {
        var rest: Rest? = null
        var websocket: Websocket? = null
    }

    class Rest {
        var usdm: String? = null
        var coinm: String? = null
    }

    class Websocket {
        var usdm: String? = null
        var coinm: String? = null
        var websocketApi: String? = null
    }
}
