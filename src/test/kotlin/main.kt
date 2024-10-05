package test

import kotlinx.serialization.Serializable

@Serializable
data class Kfile(val name: String, val content: String)
//
//
//
fun main() {
//	val x = json.decodeFromString<List<Kfile>>(File("fs.kfs.jsonc").readText())
//	println(x)
}