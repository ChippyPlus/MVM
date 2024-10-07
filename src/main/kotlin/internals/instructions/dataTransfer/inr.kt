package internals.instructions.dataTransfer

import data.registers.enumIdenifiers.SuperRegisterType
import helpers.fullRegisterReadUnsafe
import helpers.fullRegisterWrite


// is null register
fun DataTransfer.inr(register: SuperRegisterType) {
	if (fullRegisterReadUnsafe(register) == null) {
		fullRegisterWrite(SuperRegisterType.IF4, 0)
	} else {
		fullRegisterWrite(SuperRegisterType.IF4, 1)
	}
}