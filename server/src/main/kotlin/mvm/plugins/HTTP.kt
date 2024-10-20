package mvm.plugins

import io.ktor.server.application.*
import io.ktor.server.plugins.defaultheaders.*
import io.ktor.server.plugins.httpsredirect.*
import io.ktor.server.response.*

fun Application.configureHTTP() {
	install(DefaultHeaders) {
		header("X-Engine", "Ktor") // will send this header with each response
	}
	install(HttpsRedirect) {
		// The port to redirect to. By default 443, the default HTTPS port.
		sslPort = 443
		// 301 Moved Permanently, or 302 Found redirect.
		permanentRedirect = true
	}
}
