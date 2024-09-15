package internals.instructions.strings

import org.example.data.registers.enumIdenifiers.SuperRegisterType
import org.example.environment.VMErrors
import org.example.errors
import org.example.helpers.readRegisterString
import org.example.helpers.writeRegisterString

fun Strings.strcat(string1: SuperRegisterType, string2: SuperRegisterType): Any = try {
    val s1: String = readRegisterString(register = string1)
    val s2: Comparable<String> = readRegisterString(register = string2)
    @Suppress("UNUSED_VARIABLE") val newString = (s1 + s2).apply {
        writeRegisterString(
            register = SuperRegisterType.R4,
            string = this@apply
        )
    }

} catch (_: Exception) {
    @Suppress("RemoveExplicitTypeArguments")
    with<VMErrors, Unit>(receiver = errors) {
        GeneralStringException("strcat")
    }
}