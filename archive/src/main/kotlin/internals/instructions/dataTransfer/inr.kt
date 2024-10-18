package internals.instructions.dataTransfer

import data.registers.RegisterType
import errors
import registers


// is null register
fun DataTransfer.inr(register: RegisterType) = try {
	if (registers.readUnsafe(register) == null) {
		registers.write(register = RegisterType.IF4, value = 0)
	} else {
		registers.write(register = RegisterType.IF4, value = 1)
	}
} catch (_: Exception) {
	errors.run { this.GeneralDataTransferException(message = "Inr") }

}