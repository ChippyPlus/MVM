package internals.instructions.dataTransfer

import data.registers.enumIdenifiers.SuperRegisterType
import helpers.registerReadUnsafe
import helpers.registerWrite


// is null register
fun DataTransfer.inr(register: SuperRegisterType) {
	if (registerReadUnsafe(register) == null) {
		registerWrite(SuperRegisterType.IF4, 0)
	} else {
		registerWrite(SuperRegisterType.IF4, 1)
	}
}