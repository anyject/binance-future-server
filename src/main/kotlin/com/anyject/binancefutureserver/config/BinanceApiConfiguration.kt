package com.anyject.binancefutureserver.config

import feign.Feign
import feign.Logger
import feign.jackson.JacksonDecoder
import feign.jackson.JacksonEncoder
import feign.slf4j.Slf4jLogger
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestTemplate

@Configuration
@EnableFeignClients
class BinanceApiConfiguration (
    val applicationProperties: ApplicationProperties
        ){

    @Bean
    fun restTemplate(): RestTemplate {
        return RestTemplate()
    }

//    @Bean
//    fun binanceApiRequestInterceptor(): RequestInterceptor {
//        return BinanceApiRequestInterceptor()
//    }

    @Bean
    fun loggerLevel(): Logger.Level {
        return Logger.Level.FULL
    }

    @Bean
    fun binanceApiFeignClient(): BinanceApiFeignClient {
        return Feign.builder()
            .decoder(JacksonDecoder())
            .encoder(JacksonEncoder())
            //.requestInterceptor(binanceApiRequestInterceptor())
            .logger(Slf4jLogger(BinanceApiFeignClient::class.java))
            .logLevel(loggerLevel())
            .target(BinanceApiFeignClient::class.java, applicationProperties.defaultUrls.rest.usdm)
    }
}