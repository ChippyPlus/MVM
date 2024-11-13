package internals.instructions.dataTransfer

import data.registers.RegisterType


// is null register
fun DataTransfer.inr(register: RegisterType) = call("inr") {
	if (registers.readUnsafe(register) == null) {
		registers.write(register = RegisterType.R6, value = 1)
	} else {
		registers.write(register = RegisterType.R6, value = 0)
	}
}