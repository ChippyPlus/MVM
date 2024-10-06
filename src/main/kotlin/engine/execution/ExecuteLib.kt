package engine.execution

import engine.parser
import helpers.LibMetaData
import java.io.File

val libtest = """
	!test:1:arg1
	FUNC_ARG R4 arg1
	PRINTR R4
""".trimIndent()

val code = """
	LIT G3 10
	CALL test G3 
""".trimIndent()


class ExecuteLib(name: String) {
	private val f = File("./lib/$name.lib").readText()
	private val file = File("./lib/$name.lib")


	fun getMeta(): LibMetaData {
		val data = f.split('\n')[0]
		val name = data.split(':')[0].removePrefix("!")
		val version = data.split(':')[1].removePrefix("!")
		val args = data.substring(name.length + version.length + 2).split(':')
		return if (args[0].isEmpty()) {
			LibMetaData(name = name, version = version.toInt(), args = args.subList(1, args.size))
		} else {
			LibMetaData(name = name, version = version.toInt(), args = args)
		}
	}

	fun execute() {
		Execute().run(parser(file.readLines().subList(1, file.readLines().size)))
	}
}

fun main() {
	val e = ExecuteLib("test")
	e.execute()
}