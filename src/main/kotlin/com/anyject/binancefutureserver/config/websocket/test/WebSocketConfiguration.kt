package com.anyject.binancefutureserver.config.websocket.test

import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Component
import org.springframework.web.socket.CloseStatus
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.config.annotation.EnableWebSocket
import org.springframework.web.socket.config.annotation.WebSocketConfigurer
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry
import org.springframework.web.socket.handler.AbstractWebSocketHandler
import java.time.LocalDateTime
import java.util.*

@Configuration
@EnableWebSocket
class WebSocketConfiguration : WebSocketConfigurer
{
    override fun registerWebSocketHandlers(registry: WebSocketHandlerRegistry) {
        registry.addHandler(CustomWebSocketHandler(), "/hi")
    }
}

@Component
class CustomWebSocketHandler : AbstractWebSocketHandler() {
    private val logger = LoggerFactory.getLogger(javaClass)
    private val timer = Timer()

    override fun afterConnectionEstablished(session: WebSocketSession) {
        logger.info(">>>>>>>connect")
        scheduleTask(session)
    }

    override fun afterConnectionClosed(session:  WebSocketSession, status: CloseStatus) {
        logger.info(">>>>>>disconnected")
    }

    override fun handleTextMessage(session: WebSocketSession, message: TextMessage) {
        logger.info("Received message: ${message.payload}")
        val reply = TextMessage("Received your message: ${message.payload}")
        session.sendMessage(reply)
    }

    private fun scheduleTask(session: WebSocketSession) {
        timer.schedule(object : TimerTask() {
            override fun run() {
                val now = LocalDateTime.now().toString()
                logger.info("Sending message: $now")
                val message = TextMessage(now)
                session.sendMessage(message)
                scheduleTask(session)
            }
        }, 1000)
    }
}