package internals.instructions.xFloats

import data.registers.RegisterType

fun XFloats.itof(register: RegisterType, registerX: RegisterType) {
	val valueLong = registers.read(register)

	if (registers.readX(registerX).isDouble) {
		registers.writeX(registerX, valueLong.toDouble().toBits())
//		xLit(registerX, valueLong.value.toDouble().toBits())

	} else {
		registers.writeX(registerX, valueLong.toFloat().toBits().toLong())
//		xLit(registerX, valueLong.value.toFloat().toBits().toLong())

	}
}