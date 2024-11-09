package engine.v3
//
//
//import data.registers.RegisterType
//import hertz
//import internals.instructions.arithmetic.*
//import internals.instructions.bitwise.*
//import internals.instructions.controlFlow.jmp
//import internals.instructions.controlFlow.jnz
//import internals.instructions.controlFlow.jz
//import internals.instructions.dataTransfer.lit
//import internals.instructions.dataTransfer.mov
//import internals.instructions.ioAbstractions.printr
//import internals.instructions.ioAbstractions.prints
//import internals.instructions.memory.load
//import internals.instructions.memory.store
//import internals.instructions.stackOperations.peek
//import internals.instructions.stackOperations.pop
//import internals.instructions.stackOperations.push
//import internals.instructions.strings.*
//import vm
//import java.io.DataInputStream
//import java.io.File
//import java.io.FileInputStream
//import java.lang.Thread.sleep
//
//class ByteExecutor {
//
//	fun execute(inputMarFile: File) = try {
//		DataInputStream(FileInputStream(inputMarFile)).use { dis ->
//			while (dis.available() > 0) {
//				sleep(hertz)
//				vm.pc++  // Increment program counter *before* executing the instruction.
//
//				when (val opcode = dis.readByte().toInt()) {
//					transMapIDs.instructions["lit"]!!.code -> { // LIT instruction
//						val register = transMapIDs.uRegisters[dis.readByte().toInt().toChar()]
//						val value = when (val valueLength = dis.readByte()) {
//							1.toByte() -> dis.readByte().toLong()
//							2.toByte() -> dis.readShort().toLong()
//							4.toByte() -> dis.readInt().toLong()
//							8.toByte() -> dis.readLong()
//							else -> throw IllegalStateException("Invalid value length: $valueLength")
//						}
//						if (register != null) {
//							vm.dataTransfer.lit(register, value)
//						}
//					}
//
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
//						val address = dis.readShort().toLong()
//						vm.controlFlow.jmp(address)
//					}
//
//					transMapIDs.instructions["jz"]!!.code,
//					transMapIDs.instructions["jnz"]!!.code,
//						-> {  // JZ and JNZ instructions
//						val address = dis.readShort().toLong()
////						val register = transMapIDs.uRegisters[dis.readByte().toInt().toChar()]!!
//						when (opcode) {
//							transMapIDs.instructions["jz"]!!.code -> vm.controlFlow.jz(address)
//							transMapIDs.instructions["jnz"]!!.code -> vm.controlFlow.jnz(address)
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
//						val register = transMapIDs.uRegisters[dis.readByte().toInt().toChar()]
//						val stringValue = dis.readUTF()
//						if (register != null) {
//							vm.strings.str(register, stringValue)
//						}
//					}
//
//
//					transMapIDs.instructions["prints"]!!.code -> vm.ioAbstractions.prints() // PRINTS instruction
//
//					transMapIDs.instructions["syscall"]!!.code -> {
//						val systemCallId = dis.readByte()
//						vm.systemCall.execute(
//							RegisterType.S1, RegisterType.S2, RegisterType.S3, RegisterType.S4
//						)
//					}
//
//					else -> throw IllegalStateException("Invalid Opcode: $opcode") // Handle unknown opcodes
//				}
//
//			}
//		}
//		vm.pc = 0
//	} catch (e: Exception) {
//		// Handle runtime errors
//		throw e
//		System.err.println("Runtime execution failed: ${e.message}")
//
//	}
//}
