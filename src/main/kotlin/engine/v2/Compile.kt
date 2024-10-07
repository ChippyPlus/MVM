package engine.v2

import engine.execution.InstructData
import engine.transMapIDs

class Compile {
	var uneditedFile = ""
	fun execute(input: List<InstructData>): String {
		for (instruction in input) {
			if (instruction.name == "emptyLine") {
				continue
			} else if (instruction.name == "comment") {
				continue
			}

			uneditedFile += transMapIDs.instructions[instruction.name]
			when (instruction.name) {
				//  register add, register add
				"comment", "emptyLine" -> {}
				"add", "sub", "mul", "div", "mod", "mov", "and", "or", "xor", "shr", "shl", "strcmp", "strcat", "strcpy", "store", "lt", "gt" -> {
					radd(instruction.values[0]!!)
					radd(instruction.values[1]!!)
				}


				// value add, register add
				"jz", "jnz" -> {
					vadd(instruction.values[0]!!)
					radd(instruction.values[1]!!)
				}

				// register add, value add
				"lit" -> {
					radd(instruction.values[0]!!)
					vadd(instruction.values[1]!!)
				}

				//  value add
				"jmp" -> {
					vadd(instruction.values[0]!!)
				}
				// register add
				"printr", "push", "pop", "peek", "not", "strlen" -> {
					radd(instruction.values[0]!!)
				}

				"load" -> {
					radd(instruction.values[0]!!)
					radd(instruction.values[1]!!)
				}

				"str" -> {
					radd(instruction.values[0]!!)
					uneditedFile += instruction.values[1]!! // This is a string btw
				}
			}

			uneditedFile += (0).toChar()
		}

		return uneditedFile
	}

	private fun vadd(values: Any) {
		uneditedFile += values
	}

	private fun radd(value: Any) {
		uneditedFile += transMapIDs.registers[value]!!
	}


	@Suppress("unused") // will be used if needed
	private fun iadd(value: Any) {
		uneditedFile += transMapIDs.instructions[value]!!
	}
}