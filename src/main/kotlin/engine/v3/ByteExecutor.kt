package engine.v3


import hertz
import vm
import java.io.DataInputStream
import java.io.File
import java.io.FileInputStream
import java.lang.Thread.sleep

class ByteExecutor {
	private val compilerElements = CompilerElements()

	fun execute(inputMarFile: File) {
		DataInputStream(FileInputStream(inputMarFile)).use { dis ->
			while (dis.available() > 0) {
				sleep(hertz)
				vm.pc++  // Increment program counter *before* executing the instruction.

				when (val opcode = dis.readByte().toInt()) {
					compilerElements.instructionCodes["lit"]!! -> { // LIT instruction
						val register = compilerElements.codesToRegisters[dis.readByte().toInt()]!!
						val value = dis.readLong()

						println("Lit $register, $value")
//						vm.dataTransfer.lit(register, value)
					}


					compilerElements.instructionCodes["printr"]!!,

						-> { // Instructions with one register operand
						val register = compilerElements.codesToRegisters[dis.readByte().toInt()]!!
						println("Printr $register")
					}


//					transMapIDs.instructions["add"]!!.code,
//					transMapIDs.instructions["sub"]!!.code,
//					transMapIDs.instructions["mul"]!!.code,
//					transMapIDs.instructions["div"]!!.code,
//					transMapIDs.instructions["mod"]!!.code,
//					transMapIDs.instructions["mov"]!!.code,
//					transMapIDs.instructions["and"]!!.code,
//					transMapIDs.instructions["or"]!!.code,
//					transMapIDs.instructions["xor"]!!.code,
//					transMapIDs.instructions["shr"]!!.code,
//					transMapIDs.instructions["shl"]!!.code,
//					transMapIDs.instructions["strcmp"]!!.code,
//					transMapIDs.instructions["strcat"]!!.code,
//					transMapIDs.instructions["strcpy"]!!.code,
//					transMapIDs.instructions["store"]!!.code,
//					transMapIDs.instructions["lt"]!!.code,
//					transMapIDs.instructions["gt"]!!.code,
//					transMapIDs.instructions["load"]!!.code,
//						-> { // Instructions with two register operands
//						val register1 = transMapIDs.uRegisters[dis.readByte().toInt().toChar()]!!
//						val register2 = transMapIDs.uRegisters[dis.readByte().toInt().toChar()]!!
//
//						when (opcode) {
//							transMapIDs.instructions["add"]!!.code -> vm.arithmetic.add(register1, register2)
//							transMapIDs.instructions["sub"]!!.code -> vm.arithmetic.sub(register1, register2)
//							transMapIDs.instructions["mul"]!!.code -> vm.arithmetic.mul(register1, register2)
//							transMapIDs.instructions["div"]!!.code -> vm.arithmetic.div(register1, register2)
//							transMapIDs.instructions["mod"]!!.code -> vm.arithmetic.mod(register1, register2)
//							transMapIDs.instructions["mov"]!!.code -> vm.dataTransfer.mov(register1, register2)
//							transMapIDs.instructions["and"]!!.code -> vm.bitwise.and(register1, register2)
//							transMapIDs.instructions["or"]!!.code -> vm.bitwise.or(register1, register2)
//							transMapIDs.instructions["xor"]!!.code -> vm.bitwise.xor(register1, register2)
//							transMapIDs.instructions["shr"]!!.code -> vm.bitwise.shr(register1, register2)
//							transMapIDs.instructions["shl"]!!.code -> vm.bitwise.shl(register1, register2)
//							transMapIDs.instructions["strcmp"]!!.code -> vm.strings.strcmp(register1, register2)
//							transMapIDs.instructions["strcat"]!!.code -> vm.strings.strcat(register1, register2)
//							transMapIDs.instructions["strcpy"]!!.code -> vm.strings.strcpy(register1, register2)
//							transMapIDs.instructions["store"]!!.code -> vm.memory.store(register1, register2)
//							transMapIDs.instructions["lt"]!!.code -> vm.arithmetic.lt(register1, register2)
//							transMapIDs.instructions["gt"]!!.code -> vm.arithmetic.gt(register1, register2)
//							transMapIDs.instructions["load"]!!.code -> vm.memory.load(register1, register2)
//							else -> throw IllegalStateException("Invalid opcode in two-register branch: $opcode")
//						}
//					}
//
//					transMapIDs.instructions["jmp"]!!.code -> { // JMP instruction
//						val address = dis.readShort().toInt()
//						vm.controlFlow.jmp(address)
//					}
//
//					transMapIDs.instructions["jz"]!!.code,
//					transMapIDs.instructions["jnz"]!!.code,
//						-> {  // JZ and JNZ instructions
//						val address = dis.readShort().toInt()
//						val register = transMapIDs.uRegisters[dis.readByte().toInt().toChar()]!!
//						when (opcode) {
//							transMapIDs.instructions["jz"]!!.code -> vm.controlFlow.jz(address, register)
//							transMapIDs.instructions["jnz"]!!.code -> vm.controlFlow.jnz(address, register)
//							else -> throw IllegalStateException("Invalid opcode in conditional jump branch: $opcode") //handle error
//						}
//					}
//
//					transMapIDs.instructions["printr"]!!.code,
//					transMapIDs.instructions["push"]!!.code,
//					transMapIDs.instructions["pop"]!!.code,
//					transMapIDs.instructions["peek"]!!.code,
//					transMapIDs.instructions["not"]!!.code,
//					transMapIDs.instructions["strlen"]!!.code,
//						-> { // Instructions with one register operand
//						val register = transMapIDs.uRegisters[dis.readByte().toInt().toChar()]!!
//
//						when (opcode) {
//							transMapIDs.instructions["printr"]!!.code -> vm.ioAbstractions.printr(register)
//							transMapIDs.instructions["push"]!!.code -> vm.stackOperations.push(register)
//							transMapIDs.instructions["pop"]!!.code -> vm.stackOperations.pop(register)
//							transMapIDs.instructions["peek"]!!.code -> vm.stackOperations.peek(register)
//							transMapIDs.instructions["not"]!!.code -> vm.bitwise.not(register)
//							transMapIDs.instructions["strlen"]!!.code -> vm.strings.strlen(register)
//
//							else -> throw IllegalStateException("Invalid opcode in one-register branch: $opcode") //handle error
//						}
//					}
//
//					transMapIDs.instructions["str"]!!.code -> { // STR instruction
//						val register = transMapIDs.uRegisters[dis.readByte().toInt().toChar()]!!
//						val stringValue = dis.readUTF()
//						vm.strings.str(register, stringValue)
//					}
//
//
//					transMapIDs.instructions["prints"]!!.code -> vm.ioAbstractions.prints() // PRINTS instruction
//
//					transMapIDs.instructions["syscall"]!!.code -> {
//						val systemCallId = dis.readByte()
//						vm.systemCall.execute(
//							SuperRegisterType.S1, SuperRegisterType.S2, SuperRegisterType.S3, SuperRegisterType.S4
//						)
//					}

					else -> throw IllegalStateException("Invalid Opcode: $opcode") // Handle unknown opcodes
				}

			}
		}
		vm.pc = 0
	}
}
