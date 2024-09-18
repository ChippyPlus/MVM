package internals.instructions.bitwise

import data.registers.enumIdenifiers.SuperRegisterType
import data.registers.enumIdenifiers.SuperRegisterType.R3
import errors
import helpers.fullRegisterRead
import helpers.fullRegisterWrite

/**
 * Performs a logical right shift operation on the value in the operand register by the amount specified in the shift amount register,
 * and stores the result in the `R3` register.
 *
 * @param operand1 The [SuperRegisterType] holding the operand to be shifted.
 * @param operand2 The [SuperRegisterType] holding the shift amount.
 * @throws GeneralBitwiseException If an error occurs during the right shift operation.
 */

fun Bitwise.shr(operand1: SuperRegisterType, operand2: SuperRegisterType): Unit = try {
    fullRegisterWrite(
        register = R3, value = fullRegisterRead(register = operand1).shr(
            bitCount = fullRegisterRead(
                register = operand2
            ).toInt()
        )
    )
} catch (_: Exception) {
    errors.run { this@run.GeneralBitwiseException(message = "shr") }
}