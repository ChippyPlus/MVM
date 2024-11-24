package engine

import data.registers.RegisterType
import data.registers.toRegisterDataType
import engine.execution.InstructData
import environment.reflection.KProcess
import helpers.gatherHelp
import helpers.toRegisterType
import kotlin.system.exitProcess

fun parser(kp: KProcess, file: List<String>) {
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
		kp.vm.pc++
		val instruction = if (line.isEmpty()) "" else line[0].lowercase()
		try {
			out.add(
				element = when (instruction) {


					"xlit" -> {
						InstructData(name = "xlit", values = arrayOf(line[1].toRegisterType(), line[2]))
					}


					"settype" -> {
						InstructData(
							name = "settype", values = arrayOf(line[1].toRegisterType(), line[2].toRegisterDataType())
						)
					}


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
					"substr" -> { // Meant to leave in the string deprecation via new stdlib. But this can stay
						InstructData( // for reasons
							name = "substr", arrayOf(
								line[1].toRegisterType(), line[2].toRegisterType(), line[3].toRegisterType()
							)
						)
					}


					"str" -> {
						InstructData(
							name = "str", arrayOf(
								line[1].toRegisterType(), try {
									line.joinToString(" ").split("\"")[1]
								} catch (e: IndexOutOfBoundsException) {
									kp.vm.errors.InvalidArgumentFormatException(
										"Any", shouldBe = "String"
									)
								}

							)
						)
					}


					"syscall" -> {
						InstructData(
							name = "syscall", arrayOf(
								RegisterType.S1, RegisterType.S2, RegisterType.S3, RegisterType.S4
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

					// Register Register Register
					"mod", "add", "sub", "mul", "div", "shl", "shr", "and", "or", "xor", "pow", "xadd", "xsub", "xmul", "xdiv", "xpow" -> {
						InstructData(
							name = instruction,
							arrayOf(line[1].toRegisterType(), line[2].toRegisterType(), line[2].toRegisterType())

						)
					}

					// Register Register
					"eq", "mov", "swp", "lt", "gt", "itof", "ftoi", "not",
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
					"printr", "peek", "pop", "push", "inr", "dealloc", "sleep" -> {
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
					"jmp", "jz", "jnz", "pushl" -> {
						InstructData(
							name = instruction, arrayOf(line[1].toLong())
						)
					}

					else -> {
						kp.vm.errors.InvalidInstructionException(instruction)
						exitProcess(99) // for kotlin. Ughhhhhh
					}
				}
			)
		} catch (missingArgument: IndexOutOfBoundsException) {
			val missingIndex = missingArgument.message!!.split(" ")[1].toByte() - 1
			val info = kp.vm.helpers.gatherHelp(instruction).arguments[missingIndex]
			kp.vm.errors.InvalidArgumentException(info = info)


		} catch (e: NumberFormatException) {
			try {
				val x = e.message!!.split(" ")[3].substring(1, e.message!!.split(" ").size - 1).toRegisterType()
				if (x == null) {
					kp.vm.errors.InvalidArgumentFormatException(badType = "Any", shouldBe = "Long")
				} else {
					kp.vm.errors.InvalidArgumentFormatException(badType = "Long", shouldBe = "Register")
				}
			} catch (_: IllegalStateException) {
				kp.vm.errors.InvalidArgumentFormatException(
					badType = "String", shouldBe = "Long"
				)
			}
		}
	}


	kp.vm.pc = 0
	kp.instructionMemory = out
}