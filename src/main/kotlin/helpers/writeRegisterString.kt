package helpers

import data.memory.MemoryAddress
import data.memory.MemoryValue
import data.registers.enumIdenifiers.SuperRegisterType
import errors
import internalMemory

/**
 * Writes a string to memory, starting at the address pointed to by the specified register.
 *
 * This function searches for a contiguous block of free memory large enough to hold the string
 * and updates the register to point to the starting address of the allocated memory.
 *
 * @param register The register that will hold the starting address of the string in memory.
 * @param string The string to write to memory.
 * @return The starting memory address where the string was written.
 * @throws MemoryAllocationException If a contiguous block of free memory large enough to hold, the string cannot be found.
 */
fun writeRegisterString(register: SuperRegisterType, string: String): Long {
    val possibleStarts = emptyMap<Long?, Any?>().toMutableMap()

    internalMemory.memory.forEach {
        possibleStarts[it.key.address] = it.value.value
    }
    possibleStarts.filter { it.value == 0 }
    val allocMem = string.length

    // Find a SPOT for a starting address for the string
    var spot: Long? = null
    for (i in possibleStarts.keys) {
        var count = 0L
        for (j in 0L..allocMem) {
            if (possibleStarts[i!! + j] == null) {
                count++
            }
        }
        if (count == allocMem + 1L) {
            spot = i
            break
        }
    }

    if (spot != null) {
        registerWrite(register, spot.toLong())
    } else {
        errors.MemoryAllocationException("Could not allocate memory for string: $string")
    }

    // Write the string char's to memory, followed by a null-terminator
    for ((index, i) in (spot!! until (spot + allocMem)).withIndex()) {
        internalMemory.memory[MemoryAddress(i)] = MemoryValue(string[index].code.toLong())
    }
    internalMemory.memory[MemoryAddress(spot + allocMem)] = MemoryValue(0)

    return spot
}