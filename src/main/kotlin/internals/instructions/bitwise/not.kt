package internals.instructions.bitwise

import data.registers.enumIdenifiers.SuperRegisterType
import data.registers.enumIdenifiers.SuperRegisterType.R3
import errors
import helpers.fullRegisterRead
import helpers.fullRegisterWrite

/**
 * Performs a bitwise NOT operation on the values in a register and stores the result in the `R3` register.
 * @param operand1 The [SuperRegisterType] holding operand.
 * @throws GeneralBitwiseException If an error occurs during the bitwise AND operation.
 */
fun Bitwise.not(operand: SuperRegisterType) = try {
    fullRegisterWrite(
        register = R3, value = fullRegisterRead(register = operand).inv()
    )
} catch (_: Exception) {
    with(errors) {
        this@with.GeneralBitwiseException("not")
    }
}