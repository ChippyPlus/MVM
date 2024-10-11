package internals.systemCalls.calls

import data.memory.MemoryAddress
import data.registers.enumIdenifiers.SuperRegisterType
import errors
import helpers.registerRead
import helpers.registerWrite
import internalMemory
import internals.systemCalls.SystemCall

fun SystemCall.arrayGet(arrayLocationV: SuperRegisterType, arrayIndexV: SuperRegisterType) {
	val metaData = internalMemory.read(MemoryAddress(registerRead(arrayLocationV))).value!!
	val index = registerRead(arrayIndexV)
	if (metaData < index) {
		errors.InvalidMemoryAddressException(index.toString())
	}
	registerWrite(
		SuperRegisterType.R2, internalMemory.read(MemoryAddress(registerRead(arrayLocationV) + 2 + index)).value!!
	)
}