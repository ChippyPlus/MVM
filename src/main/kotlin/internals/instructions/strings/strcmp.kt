package internals.instructions.strings

import org.example.data.registers.enumIdenifiers.SuperRegisterType
import org.example.helpers.fullRegisterWrite
import org.example.helpers.readRegisterString
import org.example.kvmInternals.instructions.strings.Strings

fun Strings.strcmp(string1: SuperRegisterType, string2: SuperRegisterType) {
    if (readRegisterString(string1) == readRegisterString(string2)) {
        fullRegisterWrite(SuperRegisterType.R4, 0)
    } else {
        fullRegisterWrite(SuperRegisterType.R4, 1)
    }
}