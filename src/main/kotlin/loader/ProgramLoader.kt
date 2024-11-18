package loader

import memory
import processes.Pcb
import vm.RegisterType
import java.nio.ByteBuffer
import java.nio.ByteOrder

class ProgramLoader(val pcb: Pcb) {


	fun writeInstructionToMemory(address: Long, instruction: Int, memory: ByteArray) {
		val buffer = ByteBuffer.allocate(4).order(ByteOrder.BIG_ENDIAN)
			.putInt(instruction) // Use system's endianness for consistency.
		val byteArray = buffer.array()
		for (i in byteArray.indices) {
			memory[(address.toInt()) + i] = byteArray[i]
		}
	}

	fun encodeLitInstruction(register: RegisterType, value: Int): Int {
		val opcode = 1 // Opcode for LIT
		val registerCode = register.ordinal
		// Combine fields using bitwise operations. Ensure value fits in 16 bits.
		return (opcode shl 24) or (registerCode shl 16) or (value and 0xFFFF)
	}

	fun toInstructionMemory(instructions: List<Instruction?>) {
		for (instruction in instructions) {
			if (instruction == null) continue
			when (instruction.name) {
				"lit" -> {
					println(instruction)

					val register = instruction.args[0] as RegisterType
					val value = instruction.args[1] as Int // 32-bit value

					val address = memory.allocate(8, pcb)

					// Allocate enough space for a 32-bit LIT instruction
					// No need for separate buffer2—allocate once

					val (word1, word2) = encodeLitInstruction2(register, value)
					memory.writeIntToMemory(address, word1, word2, pcb) // New writeIntToMemory for 2 words

				}


			}
		}
	}

	fun encodeLitInstruction2(register: RegisterType, value: Int): Pair<Int, Int> = Pair(register.ordinal, value)


}