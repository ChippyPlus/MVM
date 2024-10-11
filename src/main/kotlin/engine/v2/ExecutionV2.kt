package engine.v2

import data.registers.enumIdenifiers.SuperRegisterType
import data.registers.enumIdenifiers.SuperRegisterType.*
import hertz
import internals.instructions.arithmetic.*
import internals.instructions.bitwise.*
import internals.instructions.controlFlow.jmp
import internals.instructions.controlFlow.jnz
import internals.instructions.controlFlow.jz
import internals.instructions.dataTransfer.lit
import internals.instructions.dataTransfer.mov
import internals.instructions.ioAbstractions.printr
import internals.instructions.ioAbstractions.prints
import internals.instructions.memory.load
import internals.instructions.memory.store
import internals.instructions.stackOperations.peek
import internals.instructions.stackOperations.pop
import internals.instructions.stackOperations.push
import internals.instructions.strings.*
import vm
import java.lang.Thread.sleep

class ExecutionV2 {
	fun execute(f: String) {

		val nf = f.split((0).toChar()).toMutableList()
		nf.removeLast()

		val transMapIDs = TransMapIDs()
		for (instruct in nf) {
			sleep(hertz)
			vm.pc++
			when (instruct[0]) {
				'a' -> {
					vm.dataTransfer.mov(
						source = getR(instruct[1]), destination = getR(instruct[2])
					)
				}

				'b' -> {
					vm.dataTransfer.lit(
						source = getR(instruct[1]), value = instruct.substring(2).toLong()
					)
				}

				'c' -> {
					vm.arithmetic.add(
						registerA = getR(instruct[1]), registerB = getR(instruct[2])
					)
				}

				'd' -> {
					vm.arithmetic.sub(
						registerA = getR(instruct[1]), registerB = getR(instruct[2])
					)
				}

				'e' -> {
					vm.arithmetic.mul(
						registerA = getR(instruct[1]), registerB = getR(instruct[2])
					)
				}

				'f' -> {
					vm.arithmetic.div(
						registerA = getR(instruct[1]), registerB = getR(instruct[2])
					)
				}

				'g' -> {
					vm.arithmetic.mod(
						registerA = getR(instruct[1]), registerB = getR(instruct[2])
					)
				}

				'h' -> {
					vm.controlFlow.jmp(
						targetAddress = instruct.substring(1, instruct.length - 1).toInt()
					)
				}

				'i' -> {
					vm.controlFlow.jz(
						targetAddress = instruct.substring(1, instruct.length - 2).toInt(),
						testRegister = getR(instruct.last())
					)
				}

				'j' -> {
					vm.controlFlow.jnz(
						targetAddress = instruct.substring(1, instruct.length - 2).toInt(),
						testRegister = getR(instruct.last())
					)
				}

				'k' -> {
					vm.stackOperations.push(getR(instruct[1]))
				}

				'l' -> {
					vm.stackOperations.pop(getR(instruct[1]))
				}

				'm' -> {
					vm.stackOperations.peek(getR(instruct[1]))
				}

				'n' -> {
					vm.memory.store(getR(instruct[1]), getR(instruct[2]))
				}

				'o' -> {
					vm.memory.load(getR(instruct[1]), getR(instruct[2]))
				}

				'p' -> {
					vm.bitwise.shl(getR(instruct[1]), getR(instruct[2]))
				}

				'q' -> {
					vm.bitwise.shr(getR(instruct[1]), getR(instruct[2]))
				}

				'r' -> {
					vm.bitwise.and(getR(instruct[1]), getR(instruct[2]))
				}

				's' -> {
					vm.bitwise.or(getR(instruct[1]), getR(instruct[2]))
				}

				't' -> {
					vm.bitwise.xor(getR(instruct[1]), getR(instruct[2]))
				}

				'u' -> {
					vm.bitwise.not(getR(instruct[1]))
				}

				'v' -> {
					vm.ioAbstractions.prints()
				}

				'w' -> {
					vm.systemCall.execute(S0, S1, S2, S3)
				}

				'x' -> {
					vm.strings.str(getR(instruct[1]), instruct.substring(2))
				}

				'y' -> {
					vm.strings.strlen(getR(instruct[1]))
				}

				'z' -> {
					vm.strings.strcmp(getR(instruct[1]), getR(instruct[2]))
				}

				'A' -> {
					vm.strings.strcat(getR(instruct[1]), getR(instruct[2]))
				}

				'B' -> {
					vm.strings.strcpy(getR(instruct[1]), getR(instruct[2]))
				}

				'C' -> {
					vm.ioAbstractions.printr(transMapIDs.uRegisters[instruct[1]]!!)
				}

				'D' -> {
					vm.arithmetic.gt(getR(instruct[1]), getR(instruct[2]))
				}

				'E' -> {
					vm.arithmetic.lt(getR(instruct[1]), getR(instruct[2]))
				}

				else -> {
					//TODO Make it its own erro
					System.err.println("ERROR:${vm.pc - 1}: Bad symbol at runtime in MAR \"${instruct[0]}\"")
				}
			}
		}
		vm.pc = 0
	}

	private fun getR(index: Char): SuperRegisterType {
		val transMapIDs = TransMapIDs()
		return transMapIDs.uRegisters[index]!!
	}
}