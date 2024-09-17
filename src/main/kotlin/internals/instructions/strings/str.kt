package internals.instructions.strings

import data.registers.enumIdenifiers.SuperRegisterType
import environment.VMErrors
import errors
import helpers.writeRegisterString


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