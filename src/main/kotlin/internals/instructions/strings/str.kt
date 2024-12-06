package internals.instructions.strings

import data.registers.IntelRegisters
import data.registers.RegisterType
import data.registers.intelNames
import helpers.toLong
import helpers.writeClosestString

/**
 * Stores a string literal in memory and sets the specified register to point to the starting address.
 *
 * @param targetAddress The register that will hold the starting address of the string in memory.
 * @param string The string literal to be stored in memory.
 * @throws GeneralStringException If an error occurs while storing the string.
 */
fun Strings.str(targetAddress: RegisterType, string: String) {
	registers.write(intelNames[IntelRegisters.ENSF], true.toLong())

	val location = helpers.writeClosestString(string = string)
	registers.write(register = targetAddress, value = location)
}