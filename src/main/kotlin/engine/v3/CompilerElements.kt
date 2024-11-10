package engine.v3

import data.registers.RegisterType
import instructions

class CompilerElements {
	val registerCodes = createRegisterCodes()
	val instructionCodes = createInstructionCodes()
	val codesToRegisters = www()

	private fun www(): Map<Int, RegisterType> {
		val out = mutableMapOf<Int, RegisterType>()
		registerCodes.forEach { (t, u) -> out[u] = t }
		return out
	}

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

fun main() {
	CompilerElements().instructionCodes.forEach(::println)
}

