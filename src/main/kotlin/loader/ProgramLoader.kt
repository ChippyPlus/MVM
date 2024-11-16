package loader

import init
import memory
import vm.RegisterType

class ProgramLoader {
	init {
//		val x = memory.allocate(3, init)
//		println(x)
//		memory[x, init] = 99
	}


	fun toInstructionMemory(instructions: List<Instruction?>) {
		for (instruction in instructions) {
			if (instruction == null) continue
			when (instruction.name) {
				"lit" -> {
					println(instruction)
					val x = memory.allocate(3, init)
//					println(x)
					memory[x, init] = 1 // ID for lit
					memory[x + 1, init] = (instruction.args[0] as RegisterType).ordinal.toByte()
					memory[x + 2, init] = (instruction.args[1] as Int).toByte()
				}
			}
		}
	}
}