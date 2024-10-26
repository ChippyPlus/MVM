package internals.instructions.xFloats

import data.registers.RegisterType
import registers

fun XFloats.ftoi(registerX: RegisterType, register: RegisterType) {
	val valueFloat = registers.readX(registerX)
	registers.write(register, valueFloat.toLong())
}