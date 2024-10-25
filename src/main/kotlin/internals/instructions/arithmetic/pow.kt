package internals.instructions.arithmetic

import data.registers.RegisterType
import registers
import kotlin.math.pow


fun Arithmetic.pow(registerA: RegisterType, registerB: RegisterType) = call("pow") {
	val a = registers.read(register = registerA).toLong()
	val b = registers.read(register = registerB).toLong()
	val out = a.toDouble().pow(b.toDouble()).toLong()
	registers.write(RegisterType.R4, out)
}