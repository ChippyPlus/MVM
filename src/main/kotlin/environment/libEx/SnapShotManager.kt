package environment.libEx

import data.memory.MemoryAddress
import data.memory.MemoryValue
import data.registers.RegisterType
import internalMemory
import registers

class SnapShotManager {
	fun populateSnapShotRegister(snapShotRegisters: Map<RegisterType, Long?>) {
		for (i in snapShotRegisters) {
			registers.writeUnsafe(i.key, i.value)
		}
	}

	fun snapShotRegisters(): Map<RegisterType, Long?> {
		val allRegisters = mutableMapOf<RegisterType, Long?>()
		for (i in RegisterType.entries) {
			allRegisters[i] = registers.readUnsafe(i)
		}
		return allRegisters
	}

	fun snapShotMemory(): Map<MemoryAddress, MemoryValue> = internalMemory.memory

	fun populateSnapShotMemory(memory: Map<MemoryAddress, MemoryValue>) {
		internalMemory.memory = memory.toMutableMap()
	}


	fun fullSnapshot(): SnapData {
		val registers = snapShotRegisters()
		val memory = snapShotMemory()
		return SnapData(memory = memory, registers = registers)
	}

	fun populateSnapShot(data: SnapData) {
		populateSnapShotMemory(data.memory)
		populateSnapShotRegister(data.registers)
	}


}


data class SnapData(val memory: Map<MemoryAddress, MemoryValue>, val registers: Map<RegisterType, Long?>)

