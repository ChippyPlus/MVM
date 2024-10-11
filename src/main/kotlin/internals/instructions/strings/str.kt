package internals.instructions.strings

import data.registers.enumIdenifiers.SuperRegisterType
import errors
import helpers.registerWrite
import helpers.writeClosestString

/**
 * Stores a string literal in memory and sets the specified register to point to the starting address.
 *
 * @param targetAddress The register that will hold the starting address of the string in memory.
 * @param string The string literal to be stored in memory.
 * @throws GeneralStringException If an error occurs while storing the string.
 */
fun Strings.str(targetAddress: SuperRegisterType, string: String): Unit = try {
    val location = writeClosestString(string = string)
    registerWrite(targetAddress, location)
} catch (_: Exception) {
    errors.GeneralStringException(message = "str")
}