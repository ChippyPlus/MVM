package internals.systemCalls.calls

import data.memory.MemoryAddress
import data.memory.MemoryValue
import data.registers.enumIdenifiers.SuperRegisterType
import errors
import helpers.fullRegisterRead
import internalMemory
import internals.systemCalls.SystemCall

fun SystemCall.arraySet(arrayLocationV: SuperRegisterType, arrayIndexV: SuperRegisterType, valueV: SuperRegisterType) {
	val metaData = fullRegisterRead(arrayLocationV)
	val index = fullRegisterRead(arrayIndexV)
	val value = fullRegisterRead(valueV)
	if (metaData < index) {
		errors.InvalidMemoryAddressException(index.toString())
	}
	internalMemory.write(MemoryAddress(metaData + 1 + index), MemoryValue(value))
}