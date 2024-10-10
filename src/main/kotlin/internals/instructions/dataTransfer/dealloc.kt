package internals.instructions.dataTransfer

import data.memory.MemoryAddress
import data.memory.MemoryValue
import data.registers.enumIdenifiers.SuperRegisterType
import helpers.fullRegisterRead
import internalMemory


fun DataTransfer.dealloc(memAddress: SuperRegisterType) {
	internalMemory.write(MemoryAddress(fullRegisterRead(memAddress)), MemoryValue(null))
}