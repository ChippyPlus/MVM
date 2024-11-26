package internals.systemCalls.calls

import data.memory.MemoryAddress
import data.memory.MemoryValue
import data.registers.RegisterType
import helpers.findFreeMemory
import internals.systemCalls.SystemCall


/**
 * Format
 * Array Max size | Current size | values...
 */
fun SystemCall.createArray(size: RegisterType) = call("createArray") {
	val nSize = registers.read(size)
	val spot = helpers.findFreeMemory(nSize + 2)
	internalMemory.write(MemoryAddress(spot), MemoryValue(nSize))
	internalMemory.write(MemoryAddress(spot + 1), MemoryValue(0))
	registers.write(RegisterType.R2, spot)
}