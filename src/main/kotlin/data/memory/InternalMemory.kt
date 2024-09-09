package org.example.data.memory

import org.example.errors
import org.example.MEMORY_LIMIT

class InternalMemory {
    val memory = emptyMap<MemoryAddress, MemoryValue>().toMutableMap()

    init {
        for (i in 0 until MEMORY_LIMIT) {
            memory[MemoryAddress(i)] = MemoryValue(null)
        }

    }

    fun write(address: MemoryAddress, value: MemoryValue) {
        if (address.address > MEMORY_LIMIT) { // Out of bounds error
            errors.InvalidMemoryAddressException(address)
        }
        memory[address] = value
    }

    fun read(address: MemoryAddress): MemoryValue {
        if (memory[address] == MemoryValue(null)) {
            errors.NullAddressException(address)
        }
        if (address.address > MEMORY_LIMIT) { // Out of bounds error
            errors.InvalidMemoryAddressException(address)
        }
        return memory[address]!!
    }

}