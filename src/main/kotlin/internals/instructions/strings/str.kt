package internals.instructions.strings

import org.example.data.registers.enumIdenifiers.SuperRegisterType
import org.example.errors
import org.example.helpers.writeRegisterString


fun Strings.str(targetAddress: SuperRegisterType, string: String) = try {
    writeRegisterString(targetAddress,string)
} catch (_: Exception) {
    errors.GeneralStringException("str")
}