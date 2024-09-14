package org.example.helpers

import org.example.data.memory.MemoryAddress
import org.example.data.memory.MemoryValue
import org.example.errors
import org.example.internalMemory

fun writeStringSpecInMemory(string: String, destinationAddress: MemoryAddress) {
    val allocMem = string.length

    for (i in (destinationAddress.address!! until (destinationAddress.address + allocMem))) {
        if (internalMemory.memory[MemoryAddress(i)] != MemoryValue(null)) {
            errors.NotFreeMemoryException(i.toString())
        }
    }

    for ((index, i) in (destinationAddress.address until (destinationAddress.address + allocMem)).withIndex()) {
        internalMemory.memory[MemoryAddress(i)] = MemoryValue(string[index].code.toLong())
    }
    internalMemory.memory[MemoryAddress(destinationAddress.address + allocMem)] = MemoryValue(0)
}