package loader

import init
import memory

class ProgramLoader {
	fun toInstructionMemory(instructions: List<Instruction?>) {
		for (instruction in instructions) {
			if (instruction == null) continue

			when (instruction.name) {
				"lit" -> {
					val x = memory.allocate(3, init)
					println(x)
					memory.set(x, init, 1)
//					memory[x, init] = 1 // ID for lit
//					memory[x + 1, init] = (instruction.args[0] as RegisterType).ordinal.toByte()
//					memory[x + 2, init] = (instruction.args[1] as Int).toByte()
				}
			}
		}
	}
}