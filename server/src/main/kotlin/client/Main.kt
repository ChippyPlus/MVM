package client

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import mvm.PackageFormat
import mvm.routes.pDown


private val json = Json { prettyPrint = true }

suspend fun main() {
	val client = HttpClient(CIO) {


		install(ContentNegotiation) {
			json(Json {
				prettyPrint = true // Optional: For better readability in logs
				ignoreUnknownKeys = true // Optional: Ignore unknown keys in the response
			})
		}
	}

	val response: HttpResponse = client.get("http://localhost:8080/api/v1/repo/download") {
		contentType(ContentType.Application.Json)
		setBody(pDown)
	}

	val x = json.decodeFromString<PackageFormat>(response.bodyAsText())
	println(
		json.encodeToString<PackageFormat>(x)
	)
}
