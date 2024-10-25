package internals.instructions.arithmetic

import data.registers.RegisterType
import registers

/**
 * Adds the values in two registers and stores the result in the `R4` register.
 *
 * @param registerA The [RegisterType] holding the first operand.
 * @param registerB The [RegisterType] holding the second operand.
 * @throws GeneralArithmeticException If an arithmetic error occurs during the addition.
 */
fun Arithmetic.add(registerA: RegisterType, registerB: RegisterType) = call("add") {
	val a = registers.read(register = registerA).toLong()
	val b = registers.read(register = registerB).toLong()
	val out = a + b
	registers.write(RegisterType.R4, out)

	zeroFlag(out)
	signFlag(out)


}