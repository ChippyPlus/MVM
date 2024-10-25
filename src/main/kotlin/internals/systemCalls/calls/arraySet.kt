package internals.systemCalls.calls

import data.memory.MemoryAddress
import data.memory.MemoryValue
import data.registers.RegisterType
import errors
import internalMemory
import internals.systemCalls.SystemCall
import registers

fun SystemCall.arraySet(arrayLocationV: RegisterType, arrayIndexV: RegisterType, valueV: RegisterType) =
	call("arraySet") {


		val metaDataAddr = internalMemory.read(MemoryAddress(registers.read(arrayLocationV).toLong())).value!!
		val metaDataSize = internalMemory.read(MemoryAddress(registers.read(arrayLocationV).toLong() + 1)).value!!
		val index = registers.read(arrayIndexV).toLong()
		val value = registers.read(valueV).toLong()
		val maybeLastValue =
			internalMemory.readUnsafe(MemoryAddress(registers.read(arrayLocationV).toLong() + 2 + index)).value
	if (metaDataAddr < index) {
		errors.InvalidMemoryAddressException(index.toString())
	}

	// technically this array cant get smaller without removing everything but yk. Womp womp
	if (MemoryValue(maybeLastValue).value == null) {// write count if the value was updated
		internalMemory.write(MemoryAddress(registers.read(arrayLocationV).toLong() + 1), MemoryValue(metaDataSize + 1))
	} else {
		internalMemory.write(MemoryAddress(registers.read(arrayLocationV).toLong() + 1), MemoryValue(metaDataSize))
	}

		internalMemory.write(
			MemoryAddress(registers.read(arrayLocationV).toLong() + 2 + index),
			MemoryValue(value)
		) // write values

}