package mvm

import io.ktor.server.application.*
import mvm.plugins.*
import mvm.routes.configureUpload

fun main(args: Array<String>) {
	io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
	configureUpload()
	configureSecurity()
	configureSerialization()
	configureTemplating()
	configureHTTP()
	configureRouting()
}
