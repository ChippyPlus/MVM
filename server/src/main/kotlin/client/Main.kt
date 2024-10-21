package client

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
data class RequestFormat2(
	val repository: String,
	val module: String,
	val `package`: PackageFormat,
)

@Serializable
data class PackageFormat(
	val name: String,
	val version: String,
	val code: String,
	val help: HelpFormat,
	val dependencies: List<String>,
)

@Serializable
data class HelpFormat(
	val name: String,
	val arguments: List<ArgumentsFormat>,
	val info: String,
)

@Serializable
data class ArgumentsFormat(
	val name: String,
	val info: String,
)

val pRequestFormat2 = RequestFormat2(
	repository = "kar-main",
	module = "stdlib",
	`package` = PackageFormat(
		name = "println",
		version = "1.0",
		code = "LIT S0 24\nMOV F1 S1\nSTRLEN S1\nADD R4 F1\nLIT G1 10\nLIT G2 0\nLIT G3 1\nSTORE G1 R4\nADD G3 R4\nSTORE G2 R4\nSYSCALL",
		help = HelpFormat(
			name = "println",
			arguments = listOf(ArgumentsFormat(name = "F1", info = "The string to print to the screen")),
			info = "Writes a string to the screen with a new line"
		),
		dependencies = listOf()
	)
)

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
		setBody(pRequestFormat2)
	}

	println(response.bodyAsText())
}