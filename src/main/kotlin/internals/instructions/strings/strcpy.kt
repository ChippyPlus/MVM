package internals.instructions.strings

import data.memory.MemoryAddress
import data.registers.enumIdenifiers.SuperRegisterType
import errors
import helpers.registerRead
import helpers.readRegisterString
import helpers.writeStringSpecInMemory

/**
 * Copies a string from one memory location to another.
 *
 * @param source The register containing the memory address of the source string.
 * @param destination The register containing the memory address of the destination location.
 * @throws GeneralStringException If an error occurs during the string copy.
 */

fun Strings.strcpy(source: SuperRegisterType, destination: SuperRegisterType): Unit = try {
    val string: String = readRegisterString(register = source)
    val destinationAddress: Long = registerRead(register = destination)
    writeStringSpecInMemory(
        string = string, destinationAddress = MemoryAddress(address = destinationAddress)
    )
} catch (_: Exception) {
    with(receiver = errors) { this@with.GeneralStringException(message = "strcpy") }
}