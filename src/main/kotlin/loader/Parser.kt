package loader

import utils.Conversions
import utils.toRegisterType
import java.io.File


class Parser {
	val conversions = Conversions()
	private fun splitFile(f: File) = f.readLines()

	private fun matchByLine(line: List<String>): Instruction? {
		return when (line[0].lowercase() /* Instruction name*/) {
			"lit" -> {

				Instruction("lit", listOf(line[1].toRegisterType() as Any, line[2].toInt()))
			}

			"add" -> {
				Instruction(
					"add", listOf(
						line[1].toRegisterType() as Any,
						line[2].toRegisterType() as Any,
						line[3].toRegisterType() as Any
					)
				)
			}

			"printr" -> {
				Instruction("printr", listOf(line[1].toRegisterType() as Any))
			}


			else -> null
		}
	}

	private fun iterate(file: List<String>): MutableList<Instruction?> {
		val out = mutableListOf<Instruction?>()
		for (i in file) {
			out.add(matchByLine(i.split(" ")))
		}
		return out
	}


	fun parseFile(f: File): List<Instruction?> = iterate(splitFile(f))


}


data class Instruction(val name: String, val args: List<Any>)


fun main() {
	println(Parser().parseFile(File("src/main/resources/programs/main.kar")))
}