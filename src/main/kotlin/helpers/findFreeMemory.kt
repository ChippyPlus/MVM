package helpers

import errors
import internalMemory


// should work with writeClosestString
fun findFreeMemory(size: Int): Long {
	val possibleStarts = emptyMap<Long?, Any?>().toMutableMap()

	internalMemory.memory.forEach {
		possibleStarts[it.key.address] = it.value.value
	}
	possibleStarts.filter { it.value == 0 }

	// Find a SPOT for a starting address for the string
	var spot: Long? = null
	for (i in possibleStarts.keys) {
		var count = 0L
		for (j in 0L..size) {
			if (possibleStarts[i!! + j] == null) {
				count++
			}
		}
		if (count == size + 1L) {
			spot = i
			break
		}
	}

	if (spot == null) {
		errors.MemoryAllocationException("Could not allocate memory")
	}
	return spot!!
}