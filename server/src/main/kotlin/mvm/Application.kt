package mvm

import io.ktor.server.application.*
import mvm.plugins.*

fun main(args: Array<String>) {
	io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
	configureSecurity()
	configureSerialization()
	configureTemplating()
	configureHTTP()
	configureRouting()
}
