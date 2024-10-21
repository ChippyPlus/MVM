package mvm.routes

import kotlinx.serialization.json.Json
import mvm.RepositoryFormat
import java.io.File


fun main() {
	val f =
		File("/Users/adam/Library/CloudStorage/OneDrive-WynbergBoys'HighSchool/Dev/kotlin/mvm/server/src/main/resources/packages.json").readText()

	val x = Json.decodeFromString<List<RepositoryFormat>>(f)
	println(x)

}