package engine.parseEngine

import java.io.File

class Parse(f: File) {
	val file = f.readLines()
	val tokens: List<List<String>> = tokenV1Generation()
}

fun main() {
	val p = Parse(File("main.kar"))
	p.tokens.forEach(::println)
}