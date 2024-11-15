package internals.instructions.xFloats

import data.registers.RegisterType

fun XFloats.ftoi(registerX: RegisterType, register: RegisterType) {
	val valueLong = registers.readX(registerX)

	if (valueLong.isDouble) {
		if (valueLong.value == null) {
			vm.errors.NullRegisterException(registerX)
		}
		registers.writeX(register, Double.fromBits(valueLong.value!!).toLong())
	} else {
		registers.writeX(register, Float.fromBits(valueLong.value!!.toInt()).toLong())
	}
}