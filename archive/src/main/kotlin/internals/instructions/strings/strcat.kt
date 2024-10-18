package internals.instructions.strings

import data.registers.RegisterType
import errors
import helpers.readRegisterString
import helpers.writeClosestString
import registers

/**
 * Concatenates two strings and stores the resulting string in memory.
 * The address of the concatenated string is stored in the `R4` register.
 *
 * @param string1 The register containing the memory address of the first string.
 * @param string2 The register containing the memory address of the second string.
 * @throws GeneralStringException If an error occurs during the string concatenation.
 */
fun Strings.strcat(string1: RegisterType, string2: RegisterType): Any = try {
	val s1: String = readRegisterString(register = string1)
	val s2: Comparable<String> = readRegisterString(register = string2)

	val location = writeClosestString(
		string = (s1 + s2)
	)
	registers.write(RegisterType.R4, location)


} catch (_: Exception) {
	errors.GeneralStringException("strcat")

}