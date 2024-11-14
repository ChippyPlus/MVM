package engine.execution

import data.registers.RegisterDataType
import data.registers.RegisterType
import engine.parser
import helpers.RuntimeStates
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
import internals.instructions.strings.str
import internals.instructions.xFloats.*
import kotlinx.coroutines.delay
import java.io.File


class Execute(val vm: Vm) {
	suspend fun run(command: List<InstructData>) {
		while (true) {

//			println("${vm.pc}  | ${ reflection.vmTracker.groupBy(VmTracked::vm)[vm]!![0].id} -  ${vm.runtimeState}")


			when (vm.runtimeState) {
				RuntimeStates.RUNNING -> {/* pass */

				}

				RuntimeStates.PAUSED -> continue
				RuntimeStates.CANCELLED -> break
			}

			delay(hertz)

			vm.pc++

			if (vm.pc - 1 < 0) {
				vm.errors.InvalidPcValueException((vm.pc - 1).toString())
			}

			if (vm.pc - 1L == command.size.toLong()) {
				break
			}
			val name = command[(vm.pc - 1).toInt()].name
			val args = try {
				command[(vm.pc - 1).toInt()].values
			} catch (_: IndexOutOfBoundsException) {
				break
			}

			exeWhen(name, args)


		}
	}


	suspend fun execute(file: File) {
		val tokens = parser(vm, file.readLines())
		this.run(command = tokens)
	}


	suspend fun exeWhen(name: String, args: Array<Any?>): Unit? {
		when (name) {
			"sleep" -> {
				vm.misc.sleep(args[0] as RegisterType)
			}

			"ftoi" -> {
				vm.xFloats.ftoi(args[0] as RegisterType, args[1] as RegisterType)
			}

			"itof" -> {
				vm.xFloats.itof(args[0] as RegisterType, args[1] as RegisterType)
			}

			"xlit" -> {
				vm.xFloats.xLit(
					args[0] as RegisterType,
					(args[1] as String).toDoubleOrFloatBasedOnDataType(vm, args[0] as RegisterType)
				)
			}

			"xpow" -> {
				vm.xFloats.xPow(args[0] as RegisterType, args[1] as RegisterType)
			}

			"xsub" -> {
				vm.xFloats.xSub(args[0] as RegisterType, args[1] as RegisterType)
			}

			"xmul" -> {
				vm.xFloats.xMul(args[0] as RegisterType, args[1] as RegisterType)
			}

			"xdiv" -> {
				vm.xFloats.xDiv(args[0] as RegisterType, args[1] as RegisterType)
			}

			"xadd" -> {
				vm.xFloats.xAdd(args[0] as RegisterType, args[1] as RegisterType)
			}

			"settype" -> {
				vm.registers.registers[(args[0] as RegisterType)]!!.settype(args[1] as RegisterDataType)
			}

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
				return null
			}

			"inr" -> {
				vm.dataTransfer.inr((args[0] as String).toRegisterType() ?: {
					vm.errors.InvalidRegisterException(args[0] as String)
				} as RegisterType)
			}

			"call" -> {
				vm.libPc = vm.pc
				vm.libExecute.execute(args[0].toString())
			}

			"emptyLine", "comment" -> {}
			"gt" -> {
				vm.arithmetic.gt(
					operand1 = args[0] as RegisterType, operand2 = args[1] as RegisterType
				)
			}

			"lt" -> {
				vm.arithmetic.lt(
					operand1 = args[0] as RegisterType, operand2 = args[1] as RegisterType
				)
			}

			"str" -> {
				vm.strings.str(args[0].toString().toRegisterType() ?: {
					vm.errors.InvalidRegisterException(args[0] as String)
				} as RegisterType, args[1].toString())
			}

			"swp" -> {
				vm.dataTransfer.swp(
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

			"lit" -> {
				vm.dataTransfer.lit(source = args[0] as RegisterType, value = args[1] as Long)
			}

			"mov" -> {
				vm.dataTransfer.mov(
					source = args[0] as RegisterType, destination = args[1] as RegisterType
				)
			}

			"jmp" -> {
				vm.controlFlow.jmp(targetAddress = args[0] as Long - 1L)
			}

			"jz" -> {
				vm.controlFlow.jz(
					targetAddress = args[0] as Long - 1L
				)
			}

			"jnz" -> {
				vm.controlFlow.jnz(
					targetAddress = args[0] as Long - 1L
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

			"pushl" -> {
				vm.stackOperations.pushl(registerType = args[0] as Long)
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
				vm.errors.InvalidInstructionException(name)
			}
		}
		return Unit
	}
}