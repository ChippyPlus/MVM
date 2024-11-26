package helpers

fun Helpers.writeClosestString(string: String): Long {
	val spot = findFreeMemory(string.length.toLong())

	// Write the string char to memory, followed by a null-terminator
	for ((index, i) in (spot until (spot + string.length)).withIndex()) {
		vm.internalMemory.write(i, string[index].code.toLong())
	}
	vm.internalMemory.write(spot + string.length, 0)

	return spot
}