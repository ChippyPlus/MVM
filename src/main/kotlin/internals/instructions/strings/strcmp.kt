package internals.instructions.strings

import org.example.data.registers.enumIdenifiers.SuperRegisterType
import org.example.errors
import org.example.helpers.fullRegisterWrite
import org.example.helpers.readRegisterString

fun Strings.strcmp(string1: SuperRegisterType, string2: SuperRegisterType) = try {
    if (readRegisterString(string1) == readRegisterString(string2)) {
        fullRegisterWrite(SuperRegisterType.R4, 0)
    } else {
        fullRegisterWrite(SuperRegisterType.R4, 1)
    }
} catch (_: Exception) {
    errors.GeneralStringException("strcmp")
}