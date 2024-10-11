package internals.instructions.dataTransfer

import data.memory.MemoryAddress
import data.memory.MemoryValue
import data.registers.enumIdenifiers.SuperRegisterType
import helpers.registerRead
import internalMemory


fun DataTransfer.dealloc(memAddress: SuperRegisterType) {
	internalMemory.write(MemoryAddress(registerRead(memAddress)), MemoryValue(null))
}