package app.sandbox.xp.rest

import app.sandbox.xp.model.SUCCESSFUL_EMPTY_RESPONSE
import app.sandbox.xp.model.SUCCESSFUL_RESPONSE
import app.sandbox.xp.model.USER_UNAUTHORIZED_RESPONSE
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.routing.get
import io.ktor.routing.route

fun Route.news() {
    route("news") {
        get("/success") {
            call.respond(HttpStatusCode.OK, SUCCESSFUL_RESPONSE)
        }
        get("/empty") {
            call.respond(HttpStatusCode.OK, SUCCESSFUL_EMPTY_RESPONSE)
        }
        get("/unauthorized") {
            call.respond(HttpStatusCode.OK, USER_UNAUTHORIZED_RESPONSE)
        }
    }
}