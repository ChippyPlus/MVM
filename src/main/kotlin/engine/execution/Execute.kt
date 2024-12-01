package engine.execution

import data.registers.RegisterDataType
import data.registers.RegisterType
import data.registers.read
import data.registers.write
import engine.parser
import helpers.RuntimeStates
import helpers.toDoubleOrFloatBasedOnDataType
import helpers.toRegisterType
import internals.instructions.arithmetic.*
import internals.instructions.bitwise.*
import internals.instructions.controlFlow.jmp
import internals.instructions.controlFlow.jnz
import internals.instructions.controlFlow.jz
import internals.instructions.dataTransfer.lit
import internals.instructions.dataTransfer.mov
import internals.instructions.dataTransfer.swp
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
import kernel.process.KProcess


class Execute(val kp: KProcess) {

	init {
		parser(kp, kp.file.readLines())
	}

	suspend fun singleEvent(command: InstructData) {
		kp.vm.pc++
		if (kp.vm.pc - 1 < 0) {
			kp.vm.errors.InvalidPcValueException((kp.vm.pc - 1).toString())
		}


		exeWhen(command.name, command.values)


	}


	suspend fun run(command: List<InstructData>) {
		val vm = kp.vm
		while (true) {
			when (kp.runtimeState) {

				RuntimeStates.RUNNING -> {/* pass */

				}

				RuntimeStates.PAUSED -> continue
				RuntimeStates.CANCELLED -> break
			}
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

	@Deprecated(
		"Not usable with TaskManagerV2. I mean I guess it is but yk whatever",
		ReplaceWith("engine.execution.Execute.run")
	)
	suspend fun execute() {
		this.run(command = kp.instructionMemory)
	}


	suspend fun exeWhen(name: String, args: Array<Any?>): Unit? { // This has to be suspended. I know it's terrible!
		val vm = kp.vm
		kp.currentInstruction = InstructData(name, args)

		kp.debug.act()

		when (name) {

			"HALT" -> {
				// This should not be handled here but in TaskManager. Or maybe by the OS IDK!
			}

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
				vm.xFloats.xPow(args[0] as RegisterType, args[1] as RegisterType, args[2] as RegisterType)
			}

			"xsub" -> {
				vm.xFloats.xSub(args[0] as RegisterType, args[1] as RegisterType, args[2] as RegisterType)
			}

			"xmul" -> {
				vm.xFloats.xMul(args[0] as RegisterType, args[1] as RegisterType, args[2] as RegisterType)
			}

			"xdiv" -> {
				vm.xFloats.xDiv(args[0] as RegisterType, args[1] as RegisterType, args[2] as RegisterType)
			}

			"xadd" -> {
				vm.xFloats.xAdd(args[0] as RegisterType, args[1] as RegisterType, args[2] as RegisterType)
			}

			"settype" -> {
				vm.registers.registers[(args[0] as RegisterType)]!!.settype(args[1] as RegisterDataType)
			}

//			"dealloc" -> {
//				vm.dataTransfer.dealloc(args[0] as RegisterType)
//			}

			"pow" -> {
				vm.arithmetic.pow(args[0] as RegisterType, args[1] as RegisterType, args[2] as RegisterType)
			}

			"help" -> {
				vm.misc.help((args[0] as String))
			}

			"ret" -> {
				return null
			}

			"inr" -> {
				(args[0] as String).toRegisterType() ?: {
					vm.errors.InvalidRegisterException(args[0] as String)
				} as RegisterType
			}

			"call" -> {
				vm.libPc = vm.pc


				for (i in args.toList().subList(1, args.size).withIndex()) {
					val r = i.value as RegisterType
					when (i.index) {
						0 -> RegisterType.F1.write(vm, r.read(vm))
						1 -> RegisterType.F2.write(vm, r.read(vm))
						2 -> RegisterType.F3.write(vm, r.read(vm))
						3 -> RegisterType.F4.write(vm, r.read(vm))
						4 -> RegisterType.F5.write(vm, r.read(vm))
						5 -> RegisterType.F6.write(vm, r.read(vm))
						6 -> RegisterType.F7.write(vm, r.read(vm))
						7 -> RegisterType.F8.write(vm, r.read(vm))
						8 -> RegisterType.F9.write(vm, r.read(vm))
						9 -> RegisterType.F10.write(vm, r.read(vm))
						else -> vm.errors.InvalidInstructionArgumentException("To many arguments")
					}
				}


				vm.libExecute!!.execute(args[0].toString())
			}

			"emptyLine", "comment" -> {}
			"gt" -> {
				vm.arithmetic.gt(args[0] as RegisterType, args[1] as RegisterType)
			}

			"lt" -> {
				vm.arithmetic.lt(args[0] as RegisterType, args[1] as RegisterType)
			}

			"str" -> {
				vm.strings.str(args[0].toString().toRegisterType() ?: {
					vm.errors.InvalidRegisterException(args[0] as String)
				} as RegisterType, args[1].toString())
			}

			"swp" -> {
				vm.dataTransfer.swp(args[0] as RegisterType, args[1] as RegisterType)
			}

			"add" -> {
				vm.arithmetic.add(args[0] as RegisterType, args[1] as RegisterType, args[2] as RegisterType)
			}

			"sub" -> {
				vm.arithmetic.sub(args[0] as RegisterType, args[1] as RegisterType, args[2] as RegisterType)
			}

			"mul" -> {
				vm.arithmetic.mul(args[0] as RegisterType, args[1] as RegisterType, args[2] as RegisterType)
			}

			"div" -> {
				vm.arithmetic.div(args[0] as RegisterType, args[1] as RegisterType, args[2] as RegisterType)
			}

			"mod" -> {
				vm.arithmetic.mod(args[0] as RegisterType, args[1] as RegisterType, args[2] as RegisterType)
			}

			"eq" -> {
				vm.arithmetic.eq(args[0] as RegisterType, args[1] as RegisterType)
			}

			"lit" -> {
				vm.dataTransfer.lit(args[0] as RegisterType, args[1] as Long)
			}

			"mov" -> {
				vm.dataTransfer.mov(args[0] as RegisterType, args[1] as RegisterType)
			}

			"jmp" -> {
				vm.controlFlow.jmp(args[0] as Long - 1L)
			}

			"jz" -> {
				vm.controlFlow.jz(args[0] as Long - 1L)
			}

			"jnz" -> {
				vm.controlFlow.jnz(args[0] as Long - 1L)
			}

			"peek" -> {
				vm.stackOperations.peek(args[0] as RegisterType)
			}

			"pop" -> {
				vm.stackOperations.pop(args[0] as RegisterType)
			}

			"push" -> {
				vm.stackOperations.push(args[0] as RegisterType)
			}

			"pushl" -> {
				vm.stackOperations.pushl(args[0] as Long)
			}

			"store" -> {
				vm.memory.store(
					source = args[0] as RegisterType, args[1] as RegisterType
				)
			}

			"load" -> {
				vm.memory.load(args[0] as RegisterType, args[1] as RegisterType)
			}

			"shl" -> {
				vm.bitwise.shl(args[0] as RegisterType, args[1] as RegisterType, args[2] as RegisterType)
			}

			"shr" -> {
				vm.bitwise.shr(args[0] as RegisterType, args[1] as RegisterType, args[2] as RegisterType)
			}

			"and" -> {
				vm.bitwise.and(args[0] as RegisterType, args[1] as RegisterType, args[2] as RegisterType)
			}

			"not" -> {
				vm.bitwise.not(args[0] as RegisterType, args[2] as RegisterType)
			}

			"or" -> {
				vm.bitwise.or(args[0] as RegisterType, args[1] as RegisterType, args[2] as RegisterType)
			}

			"xor" -> {
				vm.bitwise.xor(args[0] as RegisterType, args[1] as RegisterType, args[2] as RegisterType)
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