
package internals.instructions.arithmetic

import data.registers.RegisterType
import registers

/**
 * Multiplies the values in two registers and stores the result in the `R4` register.
 *
 * @param registerA The [RegisterType] holding the first operand.
 * @param registerB The [RegisterType] holding the second operand.
 * @throws GeneralArithmeticException If an arithmetic error occurs during the multiplication.
 */
fun Arithmetic.mul(registerA: RegisterType, registerB: RegisterType) = call("mul") {
	val a = registers.read(register = registerA).toLong()
	val b = registers.read(register = registerB).toLong()
	val out = a * b
	registers.write(RegisterType.R4, out)

}