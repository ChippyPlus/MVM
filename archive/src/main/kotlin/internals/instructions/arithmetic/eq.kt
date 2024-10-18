package internals.instructions.arithmetic

import data.registers.RegisterType
import data.registers.RegisterType.R4
import errors
import registers

/**
 * Compares the values in two registers for equality and sets the `R4` register accordingly.
 *
 * - If the values are equal, `R4` is set to 0.
 * - If the values are not equal, `R4` is set to 1.
 *
 * @param operand1 The [RegisterType] holding the first operand.
 * @param operand2 The [RegisterType] holding the second operand.
 * @throws GeneralArithmeticException If an error occurs during the comparison.
 */
fun Arithmetic.eq(operand1: RegisterType, operand2: RegisterType) = try {
	if (registers.read(register = operand1) == registers.read(register = operand2)) {
		registers.write(R4, 0)
	} else {
		registers.write(R4, 1)
	}
} catch (e: Exception) {
	errors.run { this@run.GeneralArithmeticException(message = "eq") }
}