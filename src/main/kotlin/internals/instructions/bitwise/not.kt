package internals.instructions.bitwise

import org.example.data.registers.enumIdenifiers.SuperRegisterType
import org.example.data.registers.enumIdenifiers.SuperRegisterType.R3
import org.example.errors
import org.example.helpers.fullRegisterRead
import org.example.helpers.fullRegisterWrite


fun Bitwise.not(operand1: SuperRegisterType) = try {
    fullRegisterWrite(
        register = R3,
        value = fullRegisterRead(register = operand1).inv()
    )
} catch (_: Exception) {
    with(errors) {
        this@with.GeneralBitwiseException("not")
    }
}