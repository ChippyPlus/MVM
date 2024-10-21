package mvm.routes

import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.Serializable

@Serializable
data class RequestPackage(
	val repository: String,
	val module: String,
	val `package`: String,
	val name: String,
	val version: String,
)

fun Application.configureUpload() {
	routing {
		post("/api/v1/repo/upload") {
			val body = call.receive<RequestFormat>()

			try {
				Upload().fullAddPackage(body)
				call.respondText("SUCCESS")
			} catch (e: IllegalAccessError) {
				call.respondText("already exists error")
			} catch (e: Exception) {
				call.respond("ERROR")
			}
		}

		get("/api/v1/repo/download") {
			val body = call.receive<RequestPackage>()

			try {
				val o = Upload().download(body)
				call.respond(o!!)
			} catch (e: NullPointerException) {
				call.respond("ERROR: Not found")
			}

		}

	}
}