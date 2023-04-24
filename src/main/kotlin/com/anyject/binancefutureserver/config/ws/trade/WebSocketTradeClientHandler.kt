package com.anyject.binancefutureserver.config.ws.trade


import com.anyject.binancefutureserver.utils.Slf4j
import org.springframework.stereotype.Component
import org.springframework.web.socket.*
import java.time.LocalDateTime
import java.util.*

@Component
@Slf4j
class WebSocketTradeClientHandler: WebSocketHandler {
    val timer = Timer()

    private fun scheduleTask(session: WebSocketSession) {
        timer.schedule(object : TimerTask() {
            override fun run() {
                val now = LocalDateTime.now().toString()
                println("SSSSSSSSSSSSSSSSSending message: $now")
                val json = """
                    {
                      "id": "187d3cb2-942d-484c-8271-4e2141bbadb1",
                      "method": "time"
                    }
                """.trimIndent()
                session.sendMessage(TextMessage(json))
                scheduleTask(session)
            }
        }, 1000)
    }
    override fun afterConnectionEstablished(session: WebSocketSession) {
        println(">>>>>> afterConnectionEstablished :: $session")
        //scheduleTask(session)
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