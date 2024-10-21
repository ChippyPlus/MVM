package mvm.routes

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import mvm.PackageFormat
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


	val tmp = mutableListOf<String>()
	tmp.add(packClass.`package`.name + ":" + packClass.`package`.version)
	println(packageNeighbours.map { "${it.name}:${it.version}" })
	for (i in packageNeighbours) {

		if ("${i.name}:${i.version}" in tmp) throw IllegalAccessError("Package already exists")
		if ("${i.name}:${i.version}" !in tmp) tmp.add("${i.name}:${i.version}")
		println("${i.name}:${i.version} --- ${tmp}")

	}

	packageNeighbours.add(packClass.`package`)
	val copy = x
	copy[repositoryIndex].modules[moduleIndex].packages = packageNeighbours
	f.writeText(json.encodeToString<List<RepositoryFormat>>(copy))
}


fun Upload.download(info: RequestPackage): PackageFormat? {
	var repositoryIndex: Int? = null
	var moduleIndex: Int? = null

	for (i in x.withIndex()) {
		if (i.value.repository == info.repository) {
			repositoryIndex = i.index
			break
		}
	}
	if (repositoryIndex == null) return null

	for (i in x[repositoryIndex].modules.withIndex()) {
		if (i.value.module == info.module) {
			moduleIndex = i.index
			break
		}
	}
	if (moduleIndex == null) return null

	val packageNeighbours = x[repositoryIndex].modules[moduleIndex].packages.toMutableList()

	if ("${info.name}:${info.version}" !in packageNeighbours.map { "${info.name}:${info.version}" }) {
		throw IllegalAccessError("Package does not exist")
	}

	return x[repositoryIndex].modules[moduleIndex].packages[packageNeighbours.indexOfFirst { "${info.name}:${info.version}" == "${it.name}:${it.version}" }]
}

