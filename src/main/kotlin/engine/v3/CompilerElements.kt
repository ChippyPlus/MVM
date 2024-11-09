package engine.v3

import data.registers.RegisterType

class CompilerElements {

	fun createRegisterCodes(): MutableMap<RegisterType, Int> {
		val registersToElementIse = mutableMapOf<RegisterType, Int>()
		RegisterType.entries.forEach { registersToElementIse[it] = it.ordinal }
		return registersToElementIse
	}


}


fun main() {
	CompilerElements().instructionCodes()
}