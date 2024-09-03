package org.example.data.memory

class InternalMemory {
    val maxMemory = 1024
    val memory = emptyMap<MemoryAddress, MemoryValue>().toMutableMap()

    init {
        for (i in 0 until maxMemory) {
            memory[MemoryAddress(i)] = MemoryValue(0)
        }
    }

    fun write(address: MemoryAddress, value: MemoryValue) {
        if (address.address > maxMemory) {
            throw IllegalArgumentException("Memory address out of bounds")
        }
        memory[address] = value
    }

    fun read(address: MemoryAddress): MemoryValue {
        if (address.address > maxMemory) {
            throw IllegalArgumentException("Memory address out of bounds")
        }
        return memory[address] ?: MemoryValue(0)
    }

}