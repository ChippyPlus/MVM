package reflection

import engine.execution.InstructData

val instructionBuffer = InstructionBuffer()


class InstructionBuffer {
	private var command: InstructData? = null

	operator fun set(type: ReflectionInstructionType, value: Any?) {
		when (type) {
			ReflectionInstructionType.Command -> command = value as InstructData
		}
	}

	operator fun get(type: ReflectionInstructionType): Any? {
		return when (type) {
			ReflectionInstructionType.Command -> {
				command
			}

		}
	}
}


enum class ReflectionInstructionType {
	Command
}