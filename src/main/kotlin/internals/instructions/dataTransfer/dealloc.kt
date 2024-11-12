package internals.instructions.dataTransfer

import data.memory.MemoryAddress
import data.memory.MemoryValue
import data.registers.RegisterType


fun DataTransfer.dealloc(memAddress: RegisterType) = call("dealloc") {
	internalMemory.write(MemoryAddress(registers.read(memAddress)), MemoryValue(null))
}