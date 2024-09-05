package org.example.data.memory

import org.example.errors
import org.example.memoryLimit

class InternalMemory {
    val memory = emptyMap<MemoryAddress, MemoryValue>().toMutableMap()

    init {
        for (i in 0 until memoryLimit) {
            memory[MemoryAddress(i)] = MemoryValue(0)
        }

    }

    fun write(address: MemoryAddress, value: MemoryValue) {
        if (address.address > memoryLimit) { // Out of bounds error
            errors.InvalidMemoryAddressException(address)
        }
        memory[address] = value
    }

    fun read(address: MemoryAddress): MemoryValue {
        if (address.address > memoryLimit) { // Out of bounds error
            errors.InvalidMemoryAddressException(address)
        }
        return memory[address] ?: MemoryValue(0)
    }

}