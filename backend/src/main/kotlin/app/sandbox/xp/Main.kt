package app.sandbox.xp

import app.sandbox.xp.rest.news
import com.fasterxml.jackson.databind.PropertyNamingStrategy
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.jackson.*
import io.ktor.routing.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun Application.main() {
    install(ContentNegotiation) {
        jackson {
            propertyNamingStrategy = PropertyNamingStrategy.SNAKE_CASE
        }
    }

    routing {
        news()
    }
}

fun main() {
    embeddedServer(Netty, port = 8080) {
        main()
    }.start(wait = true)
}