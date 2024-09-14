package internals.instructions.strings

import org.example.data.registers.enumIdenifiers.SuperRegisterType
import org.example.errors
import org.example.helpers.readRegisterString
import org.example.helpers.writeRegisterString

fun Strings.strcat(string1: SuperRegisterType, string2: SuperRegisterType) = try {
    val s1 = readRegisterString(string1)
    val s2 = readRegisterString(string2)
    val newString = s1 + s2
    writeRegisterString(SuperRegisterType.R4, newString)

} catch (_: Exception) {
    errors.GeneralStringException("strcat")
}