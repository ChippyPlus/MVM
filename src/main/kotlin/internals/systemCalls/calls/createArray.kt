package internals.systemCalls.calls

import data.memory.MemoryAddress
import data.memory.MemoryValue
import data.registers.enumIdenifiers.SuperRegisterType
import helpers.findFreeMemory
import helpers.registerRead
import helpers.registerWrite
import internalMemory
import internals.systemCalls.SystemCall


/**
 * Format
 * Array Max size | Current size | values...
 */
fun SystemCall.createArray(size: SuperRegisterType) {
	val nSize = registerRead(size)
	val spot = findFreeMemory(nSize + 2)
	internalMemory.write(MemoryAddress(spot), MemoryValue(nSize))
	internalMemory.write(MemoryAddress(spot + 1), MemoryValue(0))

	registerWrite(SuperRegisterType.R2, spot)
}