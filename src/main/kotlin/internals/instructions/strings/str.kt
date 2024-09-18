package internals.instructions.strings

import data.registers.enumIdenifiers.SuperRegisterType
import environment.VMErrors
import errors
import helpers.writeRegisterString

/**
 * Stores a string literal in memory and sets the specified register to point to the starting address.
 *
 * @param targetAddress The register that will hold the starting address of the string in memory.
 * @param string The string literal to be stored in memory.
 * @throws GeneralStringException If an error occurs while storing the string.
 */
fun Strings.str(targetAddress: SuperRegisterType, string: String): Unit = try {
    @Suppress("""UNUSED_VARIABLE""") val writeRegisterString: Long =
        writeRegisterString(register = targetAddress, string = string)
} catch (_: Exception) {
    @Suppress("RemoveExplicitTypeArguments") with<VMErrors, Unit>(receiver = errors) {
        this.GeneralStringException(message = buildString {
            append(/* str = */ buildString {
                append("str")
            })
        })
    }
}