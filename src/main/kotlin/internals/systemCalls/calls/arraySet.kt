package internals.systemCalls.calls

import data.memory.MemoryAddress
import data.memory.MemoryValue
import data.registers.enumIdenifiers.SuperRegisterType
import errors
import helpers.registerRead
import internalMemory
import internals.systemCalls.SystemCall

fun SystemCall.arraySet(arrayLocationV: SuperRegisterType, arrayIndexV: SuperRegisterType, valueV: SuperRegisterType) {
	val metaDataAddr = internalMemory.read(MemoryAddress(registerRead(arrayLocationV))).value!!
	val metaDataSize = internalMemory.read(MemoryAddress(registerRead(arrayLocationV) + 1)).value!!
	val index = registerRead(arrayIndexV)
	val value = registerRead(valueV)
	val maybeLastValue = internalMemory.readUnsafe(MemoryAddress(registerRead(arrayLocationV) + 2 + index)).value
	if (metaDataAddr < index) {
		errors.InvalidMemoryAddressException(index.toString())
	}

	// technically this array cant get smaller without removing everything but yk. Womp womp
	if (MemoryValue(maybeLastValue).value == null) {// write count if the value was updated
		internalMemory.write(MemoryAddress(registerRead(arrayLocationV) + 1), MemoryValue(metaDataSize + 1))
	} else {
		internalMemory.write(MemoryAddress(registerRead(arrayLocationV) + 1), MemoryValue(metaDataSize))
	}

	internalMemory.write(MemoryAddress(registerRead(arrayLocationV) + 2 + index), MemoryValue(value)) // write values

}