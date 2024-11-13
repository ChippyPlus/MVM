package environment.libEx

import data.memory.MemoryAddress
import data.memory.MemoryValue
import data.registers.RegisterType
import internals.Vm

class SnapShotManager(val vm: Vm) {
	var safeMemory = mutableSetOf<LongRange>()

	fun populateSnapShotRegister(snapShotRegisters: Map<RegisterType, Long?>) {
		for (i in snapShotRegisters) {
			vm.registers.writeUnsafe(i.key, i.value)
		}
	}

	fun snapShotRegisters(): Map<RegisterType, Long?> {
		val allRegisters = mutableMapOf<RegisterType, Long?>()
		allRegisters.forEach { if (!it.key.name.startsWith('I')) allRegisters.remove(it.key) }
		for (i in RegisterType.entries) {
			allRegisters[i] = vm.registers.readUnsafe(i)
		}
		return allRegisters
	}

	fun snapShotMemory(): Map<MemoryAddress, MemoryValue> = vm.internalMemory.memory.toMutableMap()
	fun populateSnapShotMemory(memory: Map<MemoryAddress, MemoryValue>) {
		val internalMem = memory.toMutableMap()
		safeMemory.forEach {
			for (i in it) {
				internalMem[MemoryAddress(i)] = vm.internalMemory.memory[MemoryAddress(i)] as MemoryValue
			}
		}
		vm.internalMemory.memory = internalMem.toMutableMap()
	}


	fun fullSnapshot(): SnapData {
		val registers = snapShotRegisters()
		val memory = snapShotMemory()
		return SnapData(memory = memory, registers = registers)
	}

	fun populateSnapShot(data: SnapData) {
		populateSnapShotMemory(data.memory)
		populateSnapShotRegister(data.registers)
		safeMemory.clear()
	}


	fun memoryRequestBlock(intRange: LongRange) {
		safeMemory.add(intRange)
	}


}


data class SnapData(val memory: Map<MemoryAddress, MemoryValue>, val registers: Map<RegisterType, Long?>)

