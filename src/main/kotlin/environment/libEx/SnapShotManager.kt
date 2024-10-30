package environment.libEx

import data.registers.RegisterType
import registers

class SnapShotManager {
	fun populateSnapShot(snapShotRegisters: MutableMap<RegisterType, Long?>) {
		for (i in snapShotRegisters) {
			registers.writeUnsafe(i.key, i.value)
		}
	}

	fun snapShotRegisters(): MutableMap<RegisterType, Long?> {
		val allRegisters = mutableMapOf<RegisterType, Long?>()
		for (i in RegisterType.entries) {
			allRegisters[i] = registers.readUnsafe(i)
		}
		return allRegisters
	}
}