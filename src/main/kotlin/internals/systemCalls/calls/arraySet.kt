package internals.systemCalls.calls

import data.memory.MemoryAddress
import data.memory.MemoryValue
import data.registers.enumIdenifiers.SuperRegisterType
import errors
import helpers.registerRead
import internalMemory
import internals.systemCalls.SystemCall

fun SystemCall.arraySet(arrayLocationV: SuperRegisterType, arrayIndexV: SuperRegisterType, valueV: SuperRegisterType) {
	val metaData = internalMemory.read(MemoryAddress(registerRead(arrayLocationV))).value!!
	val index = registerRead(arrayIndexV)
	val value = registerRead(valueV)
	if (metaData < index) {
		errors.InvalidMemoryAddressException(index.toString())
	}
	internalMemory.write(MemoryAddress(registerRead(arrayLocationV) + 1 + index), MemoryValue(value))
}