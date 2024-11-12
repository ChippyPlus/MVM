package engine.execution

import data.registers.RegisterDataType
import data.registers.RegisterType
import engine.parser
import errors
import helpers.toDoubleOrFloatBasedOnDataType
import helpers.toRegisterType
import hertz
import internals.Vm
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
import internals.instructions.misc.sleep
import internals.instructions.stackOperations.peek
import internals.instructions.stackOperations.pop
import internals.instructions.stackOperations.push
import internals.instructions.stackOperations.pushl
import internals.instructions.strings.*
import internals.instructions.xFloats.*
import libExecute
import registers
import java.io.File
import java.lang.Thread.sleep


/**
 * The class responsible for executing parsed instructions.
 */
class Execute : Vm() {
	/**
	 * Executes a list of parsed [Instruction] objects.
	 *
	 * This function represents the main execution loop of the virtual machine.
	 * It iterates through the instructions, executes them one by one, and handles control flow.
	 *
	 * @param command The list of instructions to execute.
	 */
	fun run(command: List<InstructData>) {
		while (true) {
			sleep(hertz)

			this.pc++
			if (this.pc - 1L == command.size.toLong()) {
				break
			}
			val name = command[(this.pc - 1).toInt()].name
			println(this.pc)
			val args = try {
				command[(this.pc - 1).toInt()].values
			} catch (_: IndexOutOfBoundsException) {
				break
			}

			exeWhen(name, args)

		}
	}



	fun execute(file: File) {
		val tokens = parser(file.readLines())
		this.run(command = tokens)
	}


	fun exeWhen(name: String, args: Array<Any?>): Unit? {
		when (name) {

			"sleep" -> {
				this.misc.sleep(args[0] as RegisterType)
			}

			"ftoi" -> {
				this.xFloats.ftoi(args[0] as RegisterType, args[1] as RegisterType)
			}

			"itof" -> {
				this.xFloats.itof(args[0] as RegisterType, args[1] as RegisterType)
			}

			"xlit" -> {
				this.xFloats.xLit(
					args[0] as RegisterType, (args[1] as String).toDoubleOrFloatBasedOnDataType(args[0] as RegisterType)
				)
			}

			"xpow" -> {
				this.xFloats.xPow(args[0] as RegisterType, args[1] as RegisterType)
			}

			"xsub" -> {
				this.xFloats.xSub(args[0] as RegisterType, args[1] as RegisterType)
			}

			"xmul" -> {
				this.xFloats.xMul(args[0] as RegisterType, args[1] as RegisterType)
			}

			"xdiv" -> {
				this.xFloats.xDiv(args[0] as RegisterType, args[1] as RegisterType)
			}

			"xadd" -> {
				this.xFloats.xAdd(args[0] as RegisterType, args[1] as RegisterType)
			}


			"settype" -> {
				registers.registers[(args[0] as RegisterType)]!!.settype(args[1] as RegisterDataType)
			}

			"dealloc" -> {
				this.dataTransfer.dealloc(
					memAddress = args[0] as RegisterType
				)
			}

			"pow" -> {
				this.arithmetic.pow(
					registerA = args[0] as RegisterType, registerB = args[1] as RegisterType
				)
			}

			"help" -> {
				this.misc.help((args[0] as String))
			}

			"ret" -> {
				return null
			}

			"inr" -> {
				this.dataTransfer.inr((args[0] as String).toRegisterType())
			}

			"call" -> {
				this.libPc = this.pc
				libExecute.execute(args[0].toString())
			}

			"emptyLine", "comment" -> {}
			"gt" -> {
				this.arithmetic.gt(
					operand1 = args[0] as RegisterType, operand2 = args[1] as RegisterType
				)
			}

			"lt" -> {
//                    println(args.size)
				this.arithmetic.lt(
					operand1 = args[0] as RegisterType, operand2 = args[1] as RegisterType
				)
			}

			"str" -> {
				this.strings.str(args[0].toString().toRegisterType(), args[1].toString())
			}


			"cpy" -> {
				this.dataTransfer.cpy(
					register1 = args[0] as RegisterType, register2 = args[1] as RegisterType
				)
			}

			"add" -> {
				this.arithmetic.add(
					registerA = args[0] as RegisterType, registerB = args[1] as RegisterType
				)
			}

			"sub" -> {
				this.arithmetic.sub(
					registerA = args[0] as RegisterType, registerB = args[1] as RegisterType
				)
			}

			"mul" -> {
				this.arithmetic.mul(
					registerA = args[0] as RegisterType, registerB = args[1] as RegisterType
				)
			}

			"div" -> {
				this.arithmetic.div(
					registerA = args[0] as RegisterType, registerB = args[1] as RegisterType
				)
			}

			"mod" -> {
				this.arithmetic.mod(
					registerA = args[0] as RegisterType, registerB = args[1] as RegisterType
				)
			}

			"eq" -> {
				this.arithmetic.eq(
					operand1 = args[0] as RegisterType, operand2 = args[1] as RegisterType
				)
			}

			"lit" -> {
				this.dataTransfer.lit(source = args[0] as RegisterType, value = args[1] as Long)
			}

			"mov" -> {
				this.dataTransfer.mov(
					source = args[0] as RegisterType, destination = args[1] as RegisterType
				)
			}

			"jmp" -> {
				this.controlFlow.jmp(targetAddress = args[0] as Long - 1L)
			}

			"jz" -> {
				this.controlFlow.jz(
					targetAddress = args[0] as Long - 1L
				)
			}

			"jnz" -> {
				this.controlFlow.jnz(
					targetAddress = args[0] as Long - 1L
				)
			}

			"peek" -> {
				this.stackOperations.peek(destination = args[0] as RegisterType)
			}

			"pop" -> {
				this.stackOperations.pop(destination = args[0] as RegisterType)
			}

			"push" -> {
				this.stackOperations.push(registerType = args[0] as RegisterType)
			}

			"pushl" -> {
				this.stackOperations.pushl(registerType = args[0] as Long)
			}

			"store" -> {
				this.memory.store(
					source = args[0] as RegisterType, destination = args[1] as RegisterType
				)
			}

			"load" -> {
				this.memory.load(
					memoryAddress = args[0] as RegisterType, destination = args[1] as RegisterType
				)
			}

			"shl" -> {
				this.bitwise.shl(
					operand1 = args[0] as RegisterType, operand2 = args[1] as RegisterType
				)
			}

			"shr" -> {
				this.bitwise.shr(
					operand1 = args[0] as RegisterType, operand2 = args[1] as RegisterType
				)
			}

			"and" -> {
				this.bitwise.and(
					operand1 = args[0] as RegisterType, operand2 = args[1] as RegisterType
				)
			}

			"not" -> {
				this.bitwise.not(operand = args[0] as RegisterType)
			}

			"or" -> {
				this.bitwise.or(operand1 = args[0] as RegisterType, operand2 = args[1] as RegisterType)
			}

			"xor" -> {
				this.bitwise.xor(
					operand1 = args[0] as RegisterType, operand2 = args[1] as RegisterType
				)
			}

			"syscall" -> {
				this.systemCall.execute(
					callId = args[0] as RegisterType,
					s2 = args[1] as RegisterType,
					s3 = args[2] as RegisterType,
					s4 = args[3] as RegisterType
				)
			}

			"prints" -> {
				this.ioAbstractions.prints()
			}

			"printr" -> {
				this.ioAbstractions.printr(register = args[0] as RegisterType)
			}

			else -> {
				errors.InvalidInstructionException(name)
			}
		}
		return Unit
	}
}