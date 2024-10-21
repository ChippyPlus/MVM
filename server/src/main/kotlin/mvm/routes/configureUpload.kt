package mvm.routes

import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureUpload() {
	routing {
		post("/api/v1/repo/upload") {
			val body = call.receive<RequestFormat>()

			try {
				Upload().fullAddPackage(body)
				call.respondText("SUCCESS")
			} catch (e: Exception) {
				call.respond("ERROR")
			}
		}
	}
}