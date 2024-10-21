package mvm.routes

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import mvm.RepositoryFormat
import java.io.File


class Upload(
	val f: File = File("/Users/adam/Library/CloudStorage/OneDrive-WynbergBoys'HighSchool/Dev/kotlin/mvm/server/src/main/resources/packages.json"),
	val x: List<RepositoryFormat> = Json.decodeFromString<List<RepositoryFormat>>(f.readText()),
	val json: Json = Json { prettyPrint = true },

	)


fun main() {
	Upload().fullAddPackage(pRequestFormat)
}


fun Upload.fullAddPackage(packClass: RequestFormat) {
	var repositoryIndex: Int? = null
	var moduleIndex: Int? = null

	for (i in x.withIndex()) {
		if (i.value.repository == packClass.repository) {
			repositoryIndex = i.index
			break
		}
	}
	if (repositoryIndex == null) return

	for (i in x[repositoryIndex].modules.withIndex()) {
		if (i.value.module == packClass.module) {
			moduleIndex = i.index
			break
		}
	}

	if (moduleIndex == null) return

	val packageNeighbours = x[repositoryIndex].modules[moduleIndex].packages.toMutableList()
	packageNeighbours.add(packClass.`package`)
	val copy = x
	copy[repositoryIndex].modules[moduleIndex].packages = packageNeighbours
	f.writeText(json.encodeToString<List<RepositoryFormat>>(copy))

}