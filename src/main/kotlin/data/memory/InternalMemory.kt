package data.memory

import MEMORY_LIMIT
import internals.Vm

/**
 * Represents the internal memory of the virtual machine.
 *
 * The memory is a map of [MemoryAddress] to [MemoryValue], with a fixed size defined by [MEMORY_LIMIT].
 */
class InternalMemory(vm: Vm) {
	val errors = vm.errors
	var memory = emptyMap<MemoryAddress, MemoryValue>().toMutableMap()

	var linkedR: IntRange? = null
	var linedRef: InternalMemory? = null

	init {
		for (i in 0L until MEMORY_LIMIT) {
			memory[MemoryAddress(i)] = MemoryValue(null)
		}
	}

	/**
	 * Writes a [MemoryValue] to the specified [MemoryAddress].
	 *
	 * @param address The [MemoryAddress] to write to.
	 * @param value The [MemoryValue] to write.
	 * @throws InvalidMemoryAddressException If the address is out of bounds.
	 */
	@Suppress("KDocUnresolvedReference")
	fun write(address: MemoryAddress, value: MemoryValue) {
		if (address.address!!.toInt() > MEMORY_LIMIT) {
			errors.InvalidMemoryAddressException(address)
		}

		if (linkedR != null && linedRef != null && address.address in linkedR!!) {
			linedRef!!.memory[address] = value
		}

		memory[address] = value
	}

	/**
	 * Reads a [MemoryValue] from the specified [MemoryAddress].
	 *
	 * @param address The [MemoryAddress] to read from.
	 * @return The [MemoryValue] stored at the address.
	 * @throws NullAddressException If the memory at the address is null.
	 * @throws InvalidMemoryAddressException If the address is out of bounds.
	 */
	fun read(address: MemoryAddress): MemoryValue {
		if (memory[address] == MemoryValue(null)) {
			errors.NullAddressException(address)
		}
		if (address.address!!.toInt() > MEMORY_LIMIT) {
			errors.InvalidMemoryAddressException(address)
		}
		return memory[address]!!
	}


	fun readUnsafe(address: MemoryAddress): MemoryValue {
		if (address.address!!.toInt() > MEMORY_LIMIT) {
			errors.InvalidMemoryAddressException(address)
		}
		return memory[address]!!
	}

	fun link(ref: InternalMemory, range: IntRange) {
		linkedR = range
		linedRef = ref

		ref.linedRef = this
		ref.linkedR = range
	}



}