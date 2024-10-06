package internals.instructions.functions

import java.io.File

open class Functions {
	val functionsList: List<String> = File("./lib").listFiles()!!.map { it -> it.nameWithoutExtension }
}