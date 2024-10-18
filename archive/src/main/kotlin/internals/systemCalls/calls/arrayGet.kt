package internals.systemCalls.calls

import data.memory.MemoryAddress
import data.registers.RegisterType
import errors
import internalMemory
import internals.systemCalls.SystemCall
import registers

fun SystemCall.arrayGet(arrayLocationV: RegisterType, arrayIndexV: RegisterType) {
	val metaData = internalMemory.read(MemoryAddress(registers.read(arrayLocationV))).value!!
	val index = registers.read(arrayIndexV)
	if (metaData < index) {
		errors.InvalidMemoryAddressException(index.toString())
	}
	registers.write(
		RegisterType.R2, internalMemory.read(MemoryAddress(registers.read(arrayLocationV) + 2 + index)).value!!
	)
}