package environment.libEx

import data.registers.enumIdenifiers.SuperRegisterType
import environment.ExecuteLib
import helpers.registerReadUnsafe

fun ExecuteLib.snapShotRegisters(): MutableMap<SuperRegisterType, Long?> {
	val allRegisters = mutableMapOf<SuperRegisterType, Long?>()
	for (i in SuperRegisterType.entries) {
		allRegisters[i] = registerReadUnsafe(i)
	}
	return allRegisters
}