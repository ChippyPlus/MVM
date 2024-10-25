package environment.errorsCatchable

import data.registers.IntelRegisters
import data.registers.intelNames
import registers

fun nonBlockError(errorType: ErrorType) {
	registers.write(intelNames[IntelRegisters.ESF], errorType.code)
}