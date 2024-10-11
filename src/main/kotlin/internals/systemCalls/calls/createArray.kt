package internals.systemCalls.calls

import data.memory.MemoryAddress
import data.memory.MemoryValue
import data.registers.enumIdenifiers.SuperRegisterType
import helpers.findFreeMemory
import helpers.registerRead
import helpers.registerWrite
import internalMemory
import internals.systemCalls.SystemCall


fun SystemCall.createArray(size: SuperRegisterType) {
	val nSize = registerRead(size)
	val spot = findFreeMemory(nSize + 1)
	internalMemory.write(MemoryAddress(spot), MemoryValue(nSize))
	registerWrite(SuperRegisterType.R2, spot)
}