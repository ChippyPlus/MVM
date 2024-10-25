package internals.instructions.arithmetic

import data.registers.RegisterType
import registers

/**
 * Calculates the remainder of the division of registerA by registerB and stores the result in the `R4` register.
 *
 * @param registerA The [RegisterType] holding the dividend.
 * @param registerB The [RegisterType] holding the divisor.
 * @throws GeneralArithmeticException If an arithmetic error occurs during the modulo operation.
 */
fun Arithmetic.mod(registerA: RegisterType, registerB: RegisterType) = call("mod") {
	val a = registers.read(register = registerA).toLong()
	val b = registers.read(register = registerB).toLong()
	val out = a % b
	registers.write(RegisterType.R4, out)
}