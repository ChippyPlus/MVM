package internals.systemCalls.calls

import data.memory.MemoryAddress
import data.registers.enumIdenifiers.SuperRegisterType
import errors
import helpers.fullRegisterRead
import helpers.fullRegisterWrite
import internalMemory
import internals.systemCalls.SystemCall

fun SystemCall.arrayGet(arrayLocationV: SuperRegisterType, arrayIndexV: SuperRegisterType) {
	val metaData = fullRegisterRead(arrayLocationV)
	val index = fullRegisterRead(arrayIndexV)
	if (metaData < index) {
		errors.InvalidMemoryAddressException(index.toString())
	}
	fullRegisterWrite(
		SuperRegisterType.R2, internalMemory.read(MemoryAddress(metaData + 1 + index)).value!!
	)
}