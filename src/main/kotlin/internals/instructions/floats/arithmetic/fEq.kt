package internals.instructions.floats.arithmetic

import data.registers.enumIdenifiers.FloatingRegisterType
import data.registers.enumIdenifiers.SuperRegisterType
import data.registers.enumIdenifiers.SuperRegisterType.R4
import errors
import helpers.floating.floatingRegisterRead
import helpers.registerWrite
import internals.instructions.floats.Floats

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
fun Floats.fEq(operand1: FloatingRegisterType, operand2: FloatingRegisterType) = try {
    if (floatingRegisterRead(register = operand1) == floatingRegisterRead(register = operand2)) {
        registerWrite(
            register = R4, value = 0
        )
    } else {
        registerWrite(
            register = R4, value = 1
        )
    }
} catch (e: Exception) {
    errors.run { this@run.GeneralArithmeticException(message = "FEq") }
}