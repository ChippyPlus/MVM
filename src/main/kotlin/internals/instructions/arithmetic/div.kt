package internals.instructions.arithmetic

import data.registers.RegisterType
import environment.VMErrors
import errors
import registers

/**
 * Divides the value in registerA by registerB and stores the result in the `R4` register.
 *
 * @param registerA The [RegisterType] holding the dividend.
 * @param registerB The [RegisterType] holding the divisor.
 * @throws GeneralArithmeticException If an arithmetic error occurs during the division (e.g. division by zero).
 */
fun Arithmetic.div(registerA: RegisterType, registerB: RegisterType): Unit = try {
	val a: Long = registers.read(register = registerA)
	val b: Long = registers.read(register = registerB)
	val out = a / b
	registers.write(RegisterType.R4, out)

	zeroFlag(out)
	signFlag(out)

} catch (e: Exception) {
	@Suppress("RemoveExplicitTypeArguments")
	errors.run<VMErrors, Unit> { this@run.GeneralArithmeticException(message = "div") }
}