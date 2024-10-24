package engine

import data.registers.RegisterType
import engine.execution.InstructData
import errors
import helpers.gatherHelp
import helpers.toRegisterType
import helpers.toUnsafeRegisterType
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
				element = when (instruction) {

					"help" -> {
						InstructData(
							name = "help", values = arrayOf(line[1])
						)
					}

					"call" -> {
						InstructData(
							name = "call", values = arrayOf(line[1])
						)
					}


					// Register Register Register
					// What is substr? Ive never seen this. Oh well
					"substr" -> {
						InstructData(
							name = "substr", arrayOf(
								line[1].toRegisterType(),
								line[2].toRegisterType(),
								line[3].toRegisterType()
							)
						)
					}


					"str" -> {
						InstructData(
							name = "str", arrayOf(
								line[1].toRegisterType(), try {
									line.joinToString(" ").split("\"")[1]
								} catch (e: IndexOutOfBoundsException) {
									errors.InvalidArgumentFormatException(
										"Any", shouldBe = "String"
									)
								}

							)
						)
					}


					"syscall" -> {
						InstructData(
							name = "syscall", arrayOf(
								RegisterType.S0, RegisterType.S1, RegisterType.S2, RegisterType.S3
							)

						)
					}

					"" -> {
						InstructData(
							name = "emptyLine", arrayOf()
						)
					}

					"//" -> {
						InstructData(
							name = "comment", arrayOf()
						)
					}

					// Register Register
					"mod", "add", "sub", "mul", "div", "eq",
					"shl", "shr", "mov", "cpy", "and", "or",
					"xor", "find", "strcat", "strcmp", "strcpy",
					"lt", "gt", "pow",
						-> {
						InstructData(
							name = instruction, arrayOf(line[1].toRegisterType(), line[2].toRegisterType())

						)
					}


					// None
					"prints", "ret" -> {
						InstructData(
							name = "prints", emptyArray()
						)
					}


					// Register
					"not", "printr", "peek", "pop", "push", "strlen", "inr", "dealloc" -> {
						InstructData(
							name = instruction, arrayOf(line[1].toRegisterType())
						)
					}

					"store", "load" -> {
						InstructData(
							name = instruction, arrayOf(line[1].toRegisterType(), line[2].toRegisterType())
						)
					}

					"lit" -> {
						InstructData(
							name = "lit", arrayOf(line[1].toRegisterType(), line[2].toLong())
						)
					}

					// Long
					"jmp" -> {
						InstructData(
							name = "jmp", arrayOf(line[1].toInt())
						)
					}

					// Long register
					"jz", "jnz" -> {
						InstructData(
							name = instruction, arrayOf(line[1].toInt(), line[2].toRegisterType())

						)
					}


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
				e.message!!.split(" ")[3].substring(1, e.message!!.split(" ").size - 1).toUnsafeRegisterType()
				errors.InvalidArgumentFormatException(badType = "Register", shouldBe = "Long")
			} catch (_: IllegalStateException) {
				errors.InvalidArgumentFormatException(
					badType = "String", shouldBe = "Long"
				)
			}
		}
	}


	vm.pc = 0
	return out
}