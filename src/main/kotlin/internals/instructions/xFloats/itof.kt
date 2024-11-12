package internals.instructions.xFloats

import data.registers.RegisterType

fun XFloats.itof(register: RegisterType, registerX: RegisterType) {
	val valueLong = registers.readX(register)
	if (valueLong.isDouble) {
		registers.writeX(registerX, valueLong.value.toDouble().toBits())
	} else {
		registers.writeX(registerX, valueLong.value.toFloat().toBits().toLong())
	}


}