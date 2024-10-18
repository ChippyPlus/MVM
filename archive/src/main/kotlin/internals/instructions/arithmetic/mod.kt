package internals.instructions.arithmetic

import data.registers.RegisterType
import errors
import registers

/**
 * Calculates the remainder of the division of registerA by registerB and stores the result in the `R4` register.
 *
 * @param registerA The [RegisterType] holding the dividend.
 * @param registerB The [RegisterType] holding the divisor.
 * @throws GeneralArithmeticException If an arithmetic error occurs during the modulo operation.
 */
fun Arithmetic.mod(registerA: RegisterType, registerB: RegisterType): Unit = try {
	val a: Long = registers.read(register = registerA)
	val b: Long = registers.read(register = registerB)
	registers.write(RegisterType.R4, a % b)

} catch (e: Exception) {
	errors.run { this@run.GeneralArithmeticException(message = "mod") }
}