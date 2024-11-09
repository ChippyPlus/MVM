package internals.instructions.strings

import data.registers.IntelRegisters
import data.registers.RegisterType
import data.registers.intelNames
import errors
import helpers.toLong
import helpers.writeClosestString
import registers

/**
 * Stores a string literal in memory and sets the specified register to point to the starting address.
 *
 * @param targetAddress The register that will hold the starting address of the string in memory.
 * @param string The string literal to be stored in memory.
 * @throws GeneralStringException If an error occurs while storing the string.
 */
fun Strings.str(targetAddress: RegisterType, string: String): Unit = try {
	registers.write(intelNames[IntelRegisters.ENSF], true.toLong())

	val location = writeClosestString(string = string)
	registers.write(register = targetAddress, value = location)

} catch (_: Exception) {
	errors.GeneralStringException(message = "str")
}