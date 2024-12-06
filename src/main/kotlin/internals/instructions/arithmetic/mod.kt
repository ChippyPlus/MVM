package internals.instructions.arithmetic

import data.registers.RegisterType

fun Arithmetic.mod(registerA: RegisterType, registerB: RegisterType, where: RegisterType) = call("mod") {
	val a: Long = registers.read(register = registerA)
	val b: Long = registers.read(register = registerB)
	val out = a % b
	registers.write(where, out)
}