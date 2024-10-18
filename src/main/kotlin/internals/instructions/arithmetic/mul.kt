
package internals.instructions.arithmetic

import data.registers.RegisterType
import errors
import registers

/**
 * Multiplies the values in two registers and stores the result in the `R4` register.
 *
 * @param registerA The [RegisterType] holding the first operand.
 * @param registerB The [RegisterType] holding the second operand.
 * @throws GeneralArithmeticException If an arithmetic error occurs during the multiplication.
 */
fun Arithmetic.mul(registerA: RegisterType, registerB: RegisterType): Unit = try {
	val a: Long = registers.read(register = registerA)
	val b: Long = registers.read(register = registerB)
	registers.write(RegisterType.R4, a * b)
} catch (e: Exception) {
	errors.run { this@run.GeneralArithmeticException(message = "mul") }
}