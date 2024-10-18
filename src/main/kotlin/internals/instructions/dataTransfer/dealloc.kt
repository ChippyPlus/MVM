package internals.instructions.dataTransfer

import data.memory.MemoryAddress
import data.memory.MemoryValue
import data.registers.RegisterType
import errors
import internalMemory
import registers


fun DataTransfer.dealloc(memAddress: RegisterType) = try {
	internalMemory.write(MemoryAddress(registers.read(memAddress)), MemoryValue(null))
} catch (e: Exception) {
	errors.run { this.GeneralDataTransferException(message = "Dealloc") }

}