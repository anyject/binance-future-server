package com.anyject.binancefutureserver

import com.anyject.binancefutureserver.config.ApplicationProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties(ApplicationProperties::class)
class BinanceFutureServerApplication

fun main(args: Array<String>) {
    runApplication<BinanceFutureServerApplication>(*args)
}
