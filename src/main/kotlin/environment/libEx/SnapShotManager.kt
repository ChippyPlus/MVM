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

	fun snapShotMemory(): LongArray = vm.heap.m
	fun populateSnapShotMemory(memory: LongArray) {
		safeMemory.forEach {
			for (i in it) {
				memory[i.toInt()] = vm.heap.m[i.toInt()]
			}
		}
		vm.heap.m = memory
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


data class SnapData(val memory: LongArray, val registers: Map<RegisterType, Long>) {
	override fun equals(other: Any?): Boolean {
		if (this === other) return true
		if (javaClass != other?.javaClass) return false

		other as SnapData

		if (!memory.contentEquals(other.memory)) return false
		if (registers != other.registers) return false

		return true
	}

	override fun hashCode(): Int {
		var result = memory.contentHashCode()
		result = 31 * result + registers.hashCode()
		return result
	}
}

