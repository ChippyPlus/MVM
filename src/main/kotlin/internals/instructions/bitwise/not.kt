package internals.instructions.bitwise

import data.registers.enumIdenifiers.SuperRegisterType
import data.registers.enumIdenifiers.SuperRegisterType.R3
import errors
import helpers.fullRegisterRead
import helpers.fullRegisterWrite


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