package internals.instructions.arithmetic

import data.registers.RegisterType
import errors
import registers
import kotlin.math.pow


fun Arithmetic.pow(registerA: RegisterType, registerB: RegisterType): Unit = try {
	val a: Long = registers.read(register = registerA)
	val b: Long = registers.read(register = registerB)
	registers.write(RegisterType.R4, a.toDouble().pow(b.toDouble()).toLong())
} catch (e: Exception) {
	errors.run { this@run.GeneralArithmeticException(message = "pow") }
}