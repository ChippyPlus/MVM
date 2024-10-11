package internals.instructions.arithmetic

import data.registers.enumIdenifiers.SuperRegisterType
import data.registers.enumIdenifiers.SuperRegisterType.R4
import errors
import helpers.registerRead
import helpers.registerWrite

/**
 * Compares the values in two registers for equality and sets the `R4` register accordingly.
 *
 * - If the values are equal, `R4` is set to 0.
 * - If the values are not equal, `R4` is set to 1.
 *
 * @param operand1 The [SuperRegisterType] holding the first operand.
 * @param operand2 The [SuperRegisterType] holding the second operand.
 * @throws GeneralArithmeticException If an error occurs during the comparison.
 */
fun Arithmetic.eq(operand1: SuperRegisterType, operand2: SuperRegisterType) = try {
    if (registerRead(register = operand1) == registerRead(register = operand2)) {
        registerWrite(
            register = R4, value = 0
        )
    } else {
        registerWrite(
            register = R4, value = 1
        )
    }
} catch (e: Exception) {
    errors.run { this@run.GeneralArithmeticException(message = "eq") }
}