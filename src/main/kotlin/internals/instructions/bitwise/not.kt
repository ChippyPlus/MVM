package internals.instructions.bitwise

import org.example.data.registers.enumIdenifiers.SuperRegisterType
import org.example.errors
import org.example.helpers.fullRegisterRead
import org.example.helpers.fullRegisterWrite


fun Bitwise.not(operand1: SuperRegisterType) = try {
    fullRegisterWrite(
        SuperRegisterType.R3, fullRegisterRead(operand1).inv()
    )
} catch (_: Exception) {
    errors.GeneralBitwiseException("not")
}