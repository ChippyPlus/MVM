package internals.instructions.strings

import org.example.data.registers.enumIdenifiers.SuperRegisterType
import org.example.errors
import org.example.helpers.fullRegisterWrite
import org.example.helpers.readRegisterString

fun Strings.strcmp(string1: SuperRegisterType, string2: SuperRegisterType) = try {
    @Suppress("ReplaceCallWithBinaryOperator") (if (readRegisterString(register = string1).equals(
            other = readRegisterString(
                register = string2
            )
        )
    ) fullRegisterWrite(
        register = SuperRegisterType.R4, value = 0
    ) else fullRegisterWrite(register = SuperRegisterType.R4, value = 1))
} catch (_: Exception) {
    with(receiver = errors) {
        this@with.GeneralStringException("strcmp")
    }
}