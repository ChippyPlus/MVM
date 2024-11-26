package internals.instructions.dataTransfer

import data.registers.RegisterType


// is null register
@Deprecated("NO MORE NULL REGISTERS YAY!!!", replaceWith = ReplaceWith("Just remove"))
fun DataTransfer.inr(register: RegisterType) = call("inr") {
	if (registers.read(register) == 0L) {
		registers.write(register = RegisterType.R6, value = 1)
	} else {
		registers.write(register = RegisterType.R6, value = 0)
	}
}