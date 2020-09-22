package app.sandbox.xp

import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main() {
    embeddedServer(Netty, port = 8080) {

    }.start(wait = true)
}