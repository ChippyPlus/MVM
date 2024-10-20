package internals.instructions.dataTransfer

import data.registers.RegisterType
import registers


// is null register
fun DataTransfer.inr(register: RegisterType) = call("inr") {
	if (registers.readUnsafe(register) == null) {
		registers.write(register = RegisterType.IF4, value = 0)
	} else {
		registers.write(register = RegisterType.IF4, value = 1)
	}
}