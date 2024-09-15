package internals.instructions.strings

import org.example.data.registers.enumIdenifiers.SuperRegisterType
import org.example.environment.VMErrors
import org.example.errors
import org.example.helpers.writeRegisterString


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