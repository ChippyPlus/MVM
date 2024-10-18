package helpers

import data.memory.MemoryAddress
import errors
import kotlin.system.exitProcess

/**
 * Converts a [String] to a [MemoryAddress].
 *
 * @receiver The [String] to convert.
 * @return The resulting [MemoryAddress].
 * @throws InvalidMemoryAddressException If the string cannot be parsed as a valid memory address (a [Long]).
 */
@Suppress("unused") // Just in case
fun String.toMemoryAddress(): MemoryAddress {

	try {
		return MemoryAddress(this.toLong())
	} catch (e: NumberFormatException) {
		errors.InvalidMemoryAddressException(this)
		exitProcess(2)
	}
}