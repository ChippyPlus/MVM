package engine.v3

import data.registers.RegisterType
import instructions

class CompilerElements {
	val registerCodes = createRegisterCodes()
	val instructionCodes = createInstructionCodes()

	private fun createRegisterCodes(): Map<RegisterType, Int> {
		val registersToElementIse = mutableMapOf<RegisterType, Int>()
		RegisterType.entries.forEach { registersToElementIse[it] = it.ordinal }
		return registersToElementIse
	}

	private fun createInstructionCodes(): Map<String, Int> {
		val out = mutableMapOf<String, Int>()
		instructions.forEachIndexed { i, s -> out[s] = i }
		return out
	}

}

