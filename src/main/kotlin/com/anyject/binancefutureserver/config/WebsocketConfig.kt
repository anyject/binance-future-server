package com.anyject.binancefutureserver.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Component
import org.springframework.web.reactive.HandlerMapping
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping
import org.springframework.web.reactive.socket.WebSocketHandler
import org.springframework.web.reactive.socket.WebSocketSession
import org.springframework.web.reactive.socket.server.support.WebSocketHandlerAdapter
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Configuration
class WebSocketConfig {
    @Bean
    fun webSocketHandler(): WebSocketHandler = CustomWebSocketHandler()

    @Bean
    fun handlerMapping(): HandlerMapping {
        val mapping = SimpleUrlHandlerMapping()
        mapping.urlMap = mapOf("/wss/messages" to webSocketHandler())
        mapping.order = 1
        return mapping
    }

    @Bean
    fun handlerAdapter(): WebSocketHandlerAdapter = WebSocketHandlerAdapter()
}

@Component
class CustomWebSocketHandler : WebSocketHandler {
    override fun handle(session: WebSocketSession): Mono<Void> {
        val f = Flux.just("A", "B", "C", "D", "E")
            .map { session.textMessage(it) }
        return session.send(f)
    }
}