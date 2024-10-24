package engine.execution

import data.registers.RegisterType
import debugger.DebugEngine
import engine.parser
import errors
import helpers.toRegisterType
import hertz
import internals.instructions.arithmetic.*
import internals.instructions.bitwise.*
import internals.instructions.controlFlow.jmp
import internals.instructions.controlFlow.jnz
import internals.instructions.controlFlow.jz
import internals.instructions.dataTransfer.*
import internals.instructions.ioAbstractions.printr
import internals.instructions.ioAbstractions.prints
import internals.instructions.memory.load
import internals.instructions.memory.store
import internals.instructions.misc.help
import internals.instructions.stackOperations.peek
import internals.instructions.stackOperations.pop
import internals.instructions.stackOperations.push
import internals.instructions.strings.*
import libExecute
import vm
import java.io.File
import java.lang.Thread.sleep


/**
 * The class responsible for executing parsed instructions.
 */
class Execute {

	/**
	 * Executes a list of parsed [Instruction] objects.
	 *
	 * This function represents the main execution loop of the virtual machine.
	 * It iterates through the instructions, executes them one by one, and handles control flow.
	 *
	 * @param command The list of instructions to execute.
	 */
	fun run(command: List<InstructData>, usingDebugEngine: DebugEngine? = null) {

		while (true) {
			sleep(hertz)

			vm.pc++

			if (usingDebugEngine != null) {
				usingDebugEngine.eachInteraction()
				usingDebugEngine.lineSpecific()
			}
			if (vm.pc - 1 == command.size) {
				break
			}

			val args = try {
				command[vm.pc - 1].values
			} catch (_: IndexOutOfBoundsException) {
				break
			}
			when (command[vm.pc - 1].name) {

				"dealloc" -> {
					vm.dataTransfer.dealloc(
						memAddress = args[0] as RegisterType
					)
				}

				"pow" -> {
					vm.arithmetic.pow(
						registerA = args[0] as RegisterType, registerB = args[1] as RegisterType
					)
				}

				"help" -> {
					vm.misc.help((args[0] as String))
				}

				"ret" -> {
					break
				}

				"inr" -> {
					vm.dataTransfer.inr((args[0] as String).toRegisterType())
				}

				"call" -> {
					vm.libPc = vm.pc
					libExecute.execute(args[0].toString())
				}

				"emptyLine", "comment" -> {}
				"gt" -> {
					vm.arithmetic.gt(
						operand1 = args[0] as RegisterType, operand2 = args[1] as RegisterType
					)
				}

				"lt" -> {
//                    println(args.size)
					vm.arithmetic.lt(
						operand1 = args[0] as RegisterType, operand2 = args[1] as RegisterType
					)
				}

				"str" -> {
					vm.strings.str(args[0].toString().toRegisterType(), args[1].toString())
				}

				"strcmp" -> {
					vm.strings.strcmp(
						string1 = args[0] as RegisterType, string2 = args[1] as RegisterType
					)
				}

				"strcat" -> {
					vm.strings.strcat(
						string1 = args[0] as RegisterType, string2 = args[1] as RegisterType
					)
				}

				"strcpy" -> {
					vm.strings.strcpy(
						source = args[0] as RegisterType, destination = args[1] as RegisterType
					)
				}

				"cpy" -> {
					vm.dataTransfer.cpy(
						register1 = args[0] as RegisterType, register2 = args[1] as RegisterType
					)
				}

				"add" -> {
					vm.arithmetic.add(
						registerA = args[0] as RegisterType, registerB = args[1] as RegisterType
					)
				}

				"sub" -> {
					vm.arithmetic.sub(
						registerA = args[0] as RegisterType, registerB = args[1] as RegisterType
					)
				}

				"mul" -> {
					vm.arithmetic.mul(
						registerA = args[0] as RegisterType, registerB = args[1] as RegisterType
					)
				}

				"div" -> {
					vm.arithmetic.div(
						registerA = args[0] as RegisterType, registerB = args[1] as RegisterType
					)
				}

				"mod" -> {
					vm.arithmetic.mod(
						registerA = args[0] as RegisterType, registerB = args[1] as RegisterType
					)
				}

				"eq" -> {
					vm.arithmetic.eq(
						operand1 = args[0] as RegisterType, operand2 = args[1] as RegisterType
					)
				}

				"strlen" -> {
					vm.strings.strlen(addressRegister = args[0] as RegisterType)
				}

				"lit" -> {
					vm.dataTransfer.lit(source = args[0] as RegisterType, value = args[1] as Long)
				}

				"mov" -> {
					vm.dataTransfer.mov(
						source = args[0] as RegisterType, destination = args[1] as RegisterType
					)
				}

				"jmp" -> {
					vm.controlFlow.jmp(targetAddress = args[0] as Int - 2)
				}

				"jz" -> {
					vm.controlFlow.jz(
						targetAddress = args[0] as Int - 2, testRegister = args[1] as RegisterType
					)
				}

				"jnz" -> {
					vm.controlFlow.jnz(
						targetAddress = args[0] as Int - 2, testRegister = args[1] as RegisterType
					)
				}

				"peek" -> {
					vm.stackOperations.peek(destination = args[0] as RegisterType)
				}

				"pop" -> {
					vm.stackOperations.pop(destination = args[0] as RegisterType)
				}

				"push" -> {
					vm.stackOperations.push(registerType = args[0] as RegisterType)
				}

				"store" -> {
					vm.memory.store(
						source = args[0] as RegisterType, destination = args[1] as RegisterType
					)
				}

				"load" -> {
					vm.memory.load(
						memoryAddress = args[0] as RegisterType, destination = args[1] as RegisterType
					)
				}

				"shl" -> {
					vm.bitwise.shl(
						operand1 = args[0] as RegisterType, operand2 = args[1] as RegisterType
					)
				}

				"shr" -> {
					vm.bitwise.shr(
						operand1 = args[0] as RegisterType, operand2 = args[1] as RegisterType
					)
				}

				"and" -> {
					vm.bitwise.and(
						operand1 = args[0] as RegisterType, operand2 = args[1] as RegisterType
					)
				}

				"not" -> {
					vm.bitwise.not(operand = args[0] as RegisterType)
				}

				"or" -> {
					vm.bitwise.or(operand1 = args[0] as RegisterType, operand2 = args[1] as RegisterType)
				}

				"xor" -> {
					vm.bitwise.xor(
						operand1 = args[0] as RegisterType, operand2 = args[1] as RegisterType
					)
				}

				"syscall" -> {
					vm.systemCall.execute(
						callId = args[0] as RegisterType,
						s2 = args[1] as RegisterType,
						s3 = args[2] as RegisterType,
						s4 = args[3] as RegisterType
					)
				}

				"prints" -> {
					vm.ioAbstractions.prints()
				}

				"printr" -> {
					vm.ioAbstractions.printr(register = args[0] as RegisterType)
				}

				else -> {
					errors.InvalidInstructionException(command[vm.pc - 1].name)
				}
			}

		}
	}


	/**
	 * Parses and executes the instructions from the specified file.
	 *
	 * @param file The file containing the assembly code to execute.
	 */
	fun execute(file: File, usingDebugTools: DebugEngine? = null) {
		val tokens = parser(file.readLines())
		this.run(command = tokens, usingDebugEngine = usingDebugTools)
	}
}