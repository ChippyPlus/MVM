package internals.instructions.strings

import data.registers.RegisterType
import errors
import helpers.readRegisterString
import registers

/**
 * Compares two strings lexicographically and sets the `R4` register to indicate the result.
 *
 * - If the strings are equal, `R4` is set to 0.
 * - If the strings are not equal, `R4` is set to 1.
 *
 * @param string1 The register containing the memory address of the first string.
 * @param string2 The register containing the memory address of the second string.
 * @throws GeneralStringException If an error occurs during the string comparison.
 */
fun Strings.strcmp(string1: RegisterType, string2: RegisterType) = try {
	val s1 = readRegisterString(string1)
	val s2 = readRegisterString(string2)
	if (s1 == s2) {
		registers.write(
			register = RegisterType.R4, value = 0
		)
	} else {
		registers.write(register = RegisterType.R4, value = 1)
	}


} catch (_: Exception) {
	errors.GeneralStringException("Strcmp")
}