package internals.instructions.dataTransfer

import data.registers.RegisterType

fun DataTransfer.swp(register1: RegisterType, register2: RegisterType) = call("swp") {
	val value1 = registers.read(register1)
	val value2 = registers.read(register2)
	registers.write(register = register1, value = value2)
	registers.write(register = register2, value = value1)
}