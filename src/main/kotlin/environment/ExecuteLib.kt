package environment

import engine.execution.Execute
import engine.parser
import vm
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


	fun execute() {
		val oldPc = vm.pc
		Execute().run(parser(file.readLines().subList(1, file.readLines().size)))
		vm.pc = oldPc
	}
}

fun main() {
	val e = ExecuteLib("test")
	e.execute()
}