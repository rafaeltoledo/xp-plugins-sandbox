package app.sandbox.xp.rest

import app.sandbox.xp.model.DEFAULT_NEWS_COLLECTION
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.news() {
    route("news") {
        get("/") {
            call.respond(HttpStatusCode.OK, DEFAULT_NEWS_COLLECTION)
        }
    }
}