package helpers

import data.memory.MemoryAddress
import data.memory.MemoryValue

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
fun Helpers.writeClosestString(string: String): Long {
	val spot = findFreeMemory(string.length.toLong())

	// Write the string char to memory, followed by a null-terminator
	for ((index, i) in (spot until (spot + string.length)).withIndex()) {
		vm.internalMemory.memory[MemoryAddress(i)] = MemoryValue(string[index].code.toLong())
	}
	vm.internalMemory.memory[MemoryAddress(spot + string.length)] = MemoryValue(0)

	return spot
}