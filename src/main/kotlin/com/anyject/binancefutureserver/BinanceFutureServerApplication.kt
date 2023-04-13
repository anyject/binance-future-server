package com.anyject.binancefutureserver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication
@EnableFeignClients
class BinanceFutureServerApplication

fun main(args: Array<String>) {

    runApplication<BinanceFutureServerApplication>(*args)
}