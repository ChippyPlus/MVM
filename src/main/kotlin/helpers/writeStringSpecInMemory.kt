package helpers

import data.memory.MemoryAddress
import data.memory.MemoryValue
import errors
import internalMemory

/**
 * Writes a string to memory at the specified [destinationAddress].
 *
 * @param string The string to write to memory.
 * @param destinationAddress The starting [MemoryAddress] where the string will be written.
 * @throws NotFreeMemoryException If the destination memory range is not free.
 */
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