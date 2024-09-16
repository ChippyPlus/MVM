package data.memory

import MEMORY_LIMIT
import errors

class InternalMemory {
    val memory = emptyMap<MemoryAddress, MemoryValue>().toMutableMap()

    init {
        for (i in 0L until MEMORY_LIMIT) {
            memory[MemoryAddress(i)] = MemoryValue(null)
        }

    }

    fun write(address: MemoryAddress, value: MemoryValue) {
        if (address.address!!.toInt() > MEMORY_LIMIT) { // Out-of-bounds error
            errors.InvalidMemoryAddressException(address)
        }
        memory[address] = value
    }

    fun read(address: MemoryAddress): MemoryValue {
        if (memory[address] == MemoryValue(null)) {
            errors.NullAddressException(address)
        }
        if (address.address!!.toInt() > MEMORY_LIMIT) { // Out-of-bounds error
            errors.InvalidMemoryAddressException(address)
        }
        return memory[address]!!
    }

}