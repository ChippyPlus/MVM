package helpers


/**
 * Writes a string to memory at the specified [destinationAddress].
 *
 * @param string The string to write to memory.
 * @param destinationAddress The starting [MemoryAddress] where the string will be written.
 * @throws NotFreeMemoryException If the destination memory range is not free.
 */
fun Helpers.writeStringSpecInMemory(string: String, destinationAddress: Long) {
	val allocMem = string.length

	for (i in (destinationAddress until (destinationAddress + allocMem))) {
		if (internalMemory.memory[i] != 0L) {
			errors.NotFreeMemoryException(i.toString())
		}
	}

	for ((index, i) in (destinationAddress until (destinationAddress + allocMem)).withIndex()) {
		internalMemory.memory[i] = (string[index].code.toLong())
	}
	internalMemory.memory[(destinationAddress + allocMem)] = 0
}

