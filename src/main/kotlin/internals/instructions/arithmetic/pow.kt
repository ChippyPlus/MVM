package internals.instructions.arithmetic

import data.registers.RegisterType
import kotlin.math.pow


fun Arithmetic.pow(registerA: RegisterType, registerB: RegisterType) = call("pow") {
	val a: Long = registers.read(register = registerA)
	val b: Long = registers.read(register = registerB)
	val out = a.toDouble().pow(b.toDouble()).toLong()
	registers.write(RegisterType.R4, out)
}