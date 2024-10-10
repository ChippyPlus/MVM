package engine

import data.registers.enumIdenifiers.SuperRegisterType
import engine.execution.InstructData
import errors
import helpers.gatherHelp
import helpers.toSuperRegisterType
import helpers.toUnsafeSuperRegisterType
import vm
import kotlin.system.exitProcess

/**
 * Parses assembly code from a file and converts it into a list of instructions.
 *
 * @param file The file containing the assembly code.
 * @return A mutable list of [Instruction] objects representing the parsed instructions.
 * @throws InvalidInstructionException If an invalid instruction mnemonic is encountered.
 */
fun parser(file: List<String>): List<InstructData> {
	val out = emptyArray<InstructData>().toMutableList()
	val tokens = emptyList<MutableList<String>>().toMutableList()

	// Read each line from the file and split it into tokens
	for (line in file) {
		val secretLineParts = emptyList<String>().toMutableList()
		for (token in line.split(' ')) {
			if (token.isEmpty()) continue
			secretLineParts.add(token)
		}
		tokens.add(secretLineParts)
	}

	for (line in tokens) {
		vm.pc++
		val instruction = if (line.isEmpty()) "" else line[0].lowercase()
		try {
			out.add(
				when (instruction) {

					"dealloc" -> {
						InstructData(name = "dealloc", arrayOf(line[1].toSuperRegisterType()))
					}

					"pow" -> InstructData(
						name = "pow", arrayOf(line[1].toSuperRegisterType(), line[2].toSuperRegisterType())
					)

					"help" -> InstructData(
						name = "help", values = arrayOf(line[1])
					)

					"ret" -> InstructData(
						name = "ret", values = arrayOf()
					)

					"inr" -> InstructData(
						"inr", arrayOf(line[1])
					)

					"call" -> InstructData(
						"call", arrayOf(line[1])
					)

					"lt" -> InstructData(
						name = "lt", arrayOf(line[1].toSuperRegisterType(), line[2].toSuperRegisterType())
					)

					"gt" -> InstructData(
						name = "gt", arrayOf(line[1].toSuperRegisterType(), line[2].toSuperRegisterType())
					)

					"strcpy" -> InstructData(
						name = "strcpy", arrayOf(line[1].toSuperRegisterType(), line[2].toSuperRegisterType())
					)

					"strcmp" -> InstructData(
						name = "strcmp", arrayOf(line[1].toSuperRegisterType(), line[2].toSuperRegisterType())
					)

					"strcat" -> InstructData(
						name = "strcat", arrayOf(line[1].toSuperRegisterType(), line[2].toSuperRegisterType())
					)

					"substr" -> InstructData(
						name = "substr", arrayOf(
							line[1].toSuperRegisterType(), line[2].toSuperRegisterType(), line[3].toSuperRegisterType()
						)
					)

					"find" -> InstructData(
						name = "find", arrayOf(line[1].toSuperRegisterType(), line[2].toSuperRegisterType())

					)

					"cpy" -> InstructData(
						name = "cpy", arrayOf(line[1].toSuperRegisterType(), line[2].toSuperRegisterType())
					)

					"strlen" -> InstructData(
						name = "strlen", arrayOf(line[1].toSuperRegisterType())
					)

					"printr" -> InstructData(
						name = "printr", arrayOf(line[1].toSuperRegisterType())
					)

					"str" -> InstructData(
						name = "str", arrayOf(
							line[1].toSuperRegisterType(), try {
								line.joinToString(" ").split("\"")[1]
							} catch (e: IndexOutOfBoundsException) {
								errors.InvalidArgumentFormatException(
									"Any", shouldBe = "String"
								)
							}

						)
					)


					"syscall" -> InstructData(
						name = "syscall",
						arrayOf(SuperRegisterType.S1, SuperRegisterType.S2, SuperRegisterType.S3, SuperRegisterType.S4)

					)

					"" -> InstructData(
						name = "emptyLine", arrayOf()
					)


					"//" -> InstructData(
						name = "comment", arrayOf()
					)

					"mod" -> InstructData(
						name = "mod", arrayOf(line[1].toSuperRegisterType(), line[2].toSuperRegisterType())

					)

					"eq" -> InstructData(
						name = "eq", arrayOf(line[1].toSuperRegisterType(), line[2].toSuperRegisterType())
					)

					"shl" -> InstructData(
						name = "shl", arrayOf(line[1].toSuperRegisterType(), line[2].toSuperRegisterType())
					)


					"shr" -> InstructData(
						name = "shr", arrayOf(line[1].toSuperRegisterType(), line[2].toSuperRegisterType())
					)

					"peek" -> InstructData(
						name = "peek", arrayOf(line[1].toSuperRegisterType())
					)

					"pop" -> InstructData(
						name = "pop", arrayOf(line[1].toSuperRegisterType())
					)

					"push" -> InstructData(
						name = "push", arrayOf(line[1].toSuperRegisterType())
					)

					"prints" -> InstructData(
						name = "prints", emptyArray()
					)

					"div",

						-> InstructData(
						name = "div", arrayOf(line[1].toSuperRegisterType(), line[2].toSuperRegisterType())
					)


					"and",

						-> InstructData(
						name = "and", arrayOf(line[1].toSuperRegisterType(), line[2].toSuperRegisterType())
					)

					"or",

						-> InstructData(
						name = "or", arrayOf(line[1].toSuperRegisterType(), line[2].toSuperRegisterType())
					)

					"xor" -> InstructData(
						name = "xor", arrayOf(line[1].toSuperRegisterType(), line[2].toSuperRegisterType())
					)

					"not" -> InstructData(
						name = "not", arrayOf(line[1].toSuperRegisterType())
					)

					"store" -> InstructData(
						name = "store", arrayOf(line[1].toSuperRegisterType(), line[2].toSuperRegisterType())
					)

					"load" -> InstructData(
						name = "load", arrayOf(line[1].toSuperRegisterType(), line[2].toSuperRegisterType())
					)

					"lit" -> InstructData(
						name = "lit", arrayOf(line[1].toSuperRegisterType(), line[2].toLong())
					)

					"jmp" -> InstructData(
						name = "jmp", arrayOf(line[1].toInt())
					)

					"jz" -> InstructData(
						name = "jz", arrayOf(line[1].toInt(), line[2].toSuperRegisterType())

					)

					"jnz" -> InstructData(
						name = "jnz", arrayOf(line[1].toInt(), line[2].toSuperRegisterType())

					)

					"mov" -> InstructData(
						name = "mov", arrayOf(line[1].toSuperRegisterType(), line[2].toSuperRegisterType())

					)

					"add" -> InstructData(
						name = "add", arrayOf(line[1].toSuperRegisterType(), line[2].toSuperRegisterType())

					)

					"sub" -> InstructData(
						name = "sub", arrayOf(line[1].toSuperRegisterType(), line[2].toSuperRegisterType())

					)

					"mul" -> InstructData(
						name = "mul", arrayOf(line[1].toSuperRegisterType(), line[2].toSuperRegisterType())

					)

					else -> {
						errors.InvalidInstructionException(instruction)
						exitProcess(99) // for kotlin. Ughhhhhh
					}
				}
			)
		} catch (missingArgument: IndexOutOfBoundsException) {
			val missingIndex = missingArgument.message!!.split(" ")[1].toByte() - 1
			val info = gatherHelp(instruction).arguments[missingIndex]
			errors.InvalidArgumentException(info = info)
		} catch (e: NumberFormatException) {
			try {
				e.message!!.split(" ")[3].substring(1, e.message!!.split(" ").size - 1).toUnsafeSuperRegisterType()
				errors.InvalidArgumentFormatException(badType = "Register", shouldBe = "Long")
			} catch (e: IllegalStateException) {
				errors.InvalidArgumentFormatException(
					badType = "String", shouldBe = "Long"
				)
			}
		}
	}
	vm.pc = 0
	return out
}