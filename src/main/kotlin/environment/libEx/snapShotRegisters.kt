package environment.libEx

import data.registers.RegisterType
import environment.ExecuteLib
import registers

fun ExecuteLib.snapShotRegisters(): MutableMap<RegisterType, Long?> {
	val allRegisters = mutableMapOf<RegisterType, Long?>()
	for (i in RegisterType.entries) {
		allRegisters[i] = registers.readUnsafe(i)
	}
	return allRegisters
}