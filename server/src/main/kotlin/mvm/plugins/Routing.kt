package mvm.plugins

import io.ktor.resources.*
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.plugins.autohead.*
import io.ktor.server.resources.*
import io.ktor.server.resources.Resources
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.Serializable

fun Application.configureRouting() {
	install(AutoHeadResponse)
	install(Resources)
	routing {
		get("/") {
			call.respondText("Hello World!")
		}
		get<Articles> { article ->
			// Get all articles ...
			call.respond("List of articles sorted starting from ${article.sort}")
		}
		// Static plugin. Try to access `/static/index.html`
		staticResources("/static", "static")
	}
}

@Serializable
@Resource("/articles")
class Articles(val sort: String? = "new")
