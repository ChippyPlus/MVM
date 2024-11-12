package internals.instructions.xFloats

import data.registers.RegisterType

fun XFloats.ftoi(registerX: RegisterType, register: RegisterType) {
	val valueLong = registers.readX(registerX)

	if (valueLong.isDouble) {
		registers.writeX(registerX, Double.fromBits(valueLong.value).toLong())
	} else {
		registers.writeX(registerX, Float.fromBits(valueLong.value.toInt()).toLong())
	}
}