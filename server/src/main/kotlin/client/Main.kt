package client

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import mvm.routes.pRequestFormat


suspend fun main() {
	val client = HttpClient(CIO) {


	install(ContentNegotiation) {
			json(Json {
				prettyPrint = true // Optional: For better readability in logs
				ignoreUnknownKeys = true // Optional: Ignore unknown keys in the response
			})
		}
	}

	val response: HttpResponse = client.post("http://localhost:8080/api/v1/repo/upload") {
		contentType(ContentType.Application.Json)
		setBody(pRequestFormat)
	}

	println(response.bodyAsText())
}