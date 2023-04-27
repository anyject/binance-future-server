package com.anyject.binancefutureserver.config.ws.trade


import com.anyject.binancefutureserver.utils.Slf4j
import org.springframework.stereotype.Component
import org.springframework.web.socket.CloseStatus
import org.springframework.web.socket.WebSocketHandler
import org.springframework.web.socket.WebSocketMessage
import org.springframework.web.socket.WebSocketSession
import java.util.*

@Component
@Slf4j
class WebSocketTradeClientHandler: WebSocketHandler {
    val timer = Timer()

    override fun afterConnectionEstablished(session: WebSocketSession) {
        println(">>>>>> afterConnectionEstablished :: $session")
        WebSocketTradeClient.setSession(session)
    }
    override fun handleMessage(session: WebSocketSession, message: WebSocketMessage<*>) {
        println(">>>>>> handleMessage :: $session :: ${message.payload}")
    }

    override fun handleTransportError(session: WebSocketSession, exception: Throwable) {
        println(">>>>>> handleTransportError :: $session :: $exception")
    }

    override fun afterConnectionClosed(session: WebSocketSession, closeStatus: CloseStatus) {
        println(">>>>>> afterConnectionClosed :: $session :: $closeStatus")
        timer.cancel()
    }
    override fun supportsPartialMessages(): Boolean {
        return false
    }


}