package environment.libEx

import data.registers.RegisterType
import internals.Vm

class SnapShotManager(val vm: Vm) {
	var safeMemory = mutableSetOf<LongRange>()

	fun populateSnapShotRegister(snapShotRegisters: Map<RegisterType, Long>) {
		for (i in snapShotRegisters) {
			vm.registers.write(i.key, i.value)
		}
	}

	fun snapShotRegisters(): Map<RegisterType, Long> {
		val allRegisters = mutableMapOf<RegisterType, Long>()
		allRegisters.forEach { if (!it.key.name.startsWith('I')) allRegisters.remove(it.key) }
		for (i in RegisterType.entries) {
			allRegisters[i] = vm.registers.read(i)
		}
		return allRegisters
	}

	fun snapShotMemory(): Map<Long, Long> = vm.internalMemory.memory.toMutableMap()
	fun populateSnapShotMemory(memory: Map<Long, Long>) {
		val internalMem = memory.toMutableMap()
		safeMemory.forEach {
			for (i in it) {
				internalMem[i] = vm.internalMemory.memory[i] as Long
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


data class SnapData(val memory: Map<Long, Long>, val registers: Map<RegisterType, Long>)

