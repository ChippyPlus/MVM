package data.memory

import MEMORY_LIMIT
import internals.Vm

@Deprecated("This is now called the Heap!!!!")
class InternalMemory(vm: Vm) {
	val errors = vm.errors
	var memory = emptyMap<Long, Long>().toMutableMap()

	var linkedR: LongRange? = null
	var linedRef: InternalMemory? = null

	init {
		for (i in 0L until MEMORY_LIMIT) {
			memory[i] = 0
		}
	}

	fun write(address: Long, value: Long) {
		if (address.toInt() > MEMORY_LIMIT) {
			errors.InvalidMemoryAddressException(address)
		}

		if (linkedR != null && linedRef != null && address in linkedR!!) {
			linedRef!!.memory[address] = value
		}

		memory[address] = value
	}

	fun read(address: Long): Long {
		if (memory[address] == 0L) {
			errors.NullAddressException(address)
		}
		if (address > MEMORY_LIMIT) {
			errors.InvalidMemoryAddressException(address)
		}
		return memory[address]!!
	}

	@Deprecated("Should implement a new method for memoryV2")
	fun link(ref: InternalMemory, range: LongRange) {
		linkedR = range
		linedRef = ref
		ref.linedRef = this
		ref.linkedR = range

		for (i in range) {
			memory[i] = ref.memory[i]!!
		}
	}
}