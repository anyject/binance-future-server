package com.anyject.binancefutureserver

import com.anyject.binancefutureserver.config.ApplicationProperties
import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication
@EnableFeignClients
@EnableConfigurationProperties(ApplicationProperties::class)
class BinanceFutureServerApplication

fun main(args: Array<String>) {

    runApplication<BinanceFutureServerApplication>(*args)
}