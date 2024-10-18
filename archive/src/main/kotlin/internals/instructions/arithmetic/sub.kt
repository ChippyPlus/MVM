package internals.instructions.arithmetic

import data.registers.RegisterType
import errors
import registers

/**
 * Subtracts the value in registerB from registerA and stores the result in the `R4` register.
 *
 * @param registerA The [RegisterType] holding the minuend.
 * @param registerB The [RegisterType] holding the subtrahend.
 * @throws GeneralArithmeticException If an arithmetic error occurs during the subtraction.
 */

fun Arithmetic.sub(registerA: RegisterType, registerB: RegisterType): Unit = try {
	val a: Long = registers.read(register = registerA)
	val b: Long = registers.read(register = registerB)
	registers.write(RegisterType.R4, a - b)
} catch (e: Exception) {
	errors.run { this@run.GeneralArithmeticException(message = "sub") }
}