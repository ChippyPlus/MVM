package internals.instructions.strings

import data.registers.enumIdenifiers.SuperRegisterType
import errors
import helpers.readRegisterString
import helpers.registerWrite

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
fun Strings.strcmp(string1: SuperRegisterType, string2: SuperRegisterType) = try {
    @Suppress("ReplaceCallWithBinaryOperator") (if (readRegisterString(register = string1).equals(
            other = readRegisterString(
                register = string2
            )
        )
    ) registerWrite(
        register = SuperRegisterType.R4, value = 0
    ) else registerWrite(register = SuperRegisterType.R4, value = 1))
} catch (_: Exception) {
    with(receiver = errors) {
        this@with.GeneralStringException("strcmp")
    }
}