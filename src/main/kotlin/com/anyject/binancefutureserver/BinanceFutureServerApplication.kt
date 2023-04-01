package com.anyject.binancefutureserver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class BinanceFutureServerApplication

fun main(args: Array<String>) {
    println("HELLO")
    runApplication<BinanceFutureServerApplication>(*args)
}
