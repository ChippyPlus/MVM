package internals.instructions.bitwise

import data.registers.enumIdenifiers.SuperRegisterType
import data.registers.enumIdenifiers.SuperRegisterType.R3
import errors
import helpers.registerRead
import helpers.registerWrite

/**
 * Performs a bitwise AND operation on the values in two registers and stores the result in the `R3` register.
 *
 * @param operand1 The [SuperRegisterType] holding the first operand.
 * @param operand2 The [SuperRegisterType] holding the second operand.
 * @throws GeneralBitwiseException If an error occurs during the bitwise AND operation.
 */
fun Bitwise.and(operand1: SuperRegisterType, operand2: SuperRegisterType): Unit = try {
    registerWrite(
        register = R3, value = registerRead(register = operand1).and(
            other = registerRead(register = operand2)
        )
    )
} catch (_: Exception) {
    errors.run { this@run.GeneralBitwiseException(message = "add") }
}