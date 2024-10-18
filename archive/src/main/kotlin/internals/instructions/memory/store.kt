package internals.instructions.memory

import data.memory.MemoryAddress
import data.memory.MemoryValue
import data.registers.RegisterType
import errors
import internalMemory
import registers

/**
 * Stores a value from a register into memory.
 *
 * @param source The source register containing the value to store.
 * @param destination The register holding the memory address to store the value.
 * @throws GeneralMemoryException If an error occurs during the memory store operation.
 */
fun Memory.store(source: RegisterType, destination: RegisterType) = try {
	with(receiver = internalMemory) {
		this@with.write(
			address = MemoryAddress(registers.read(destination)),
			value = MemoryValue(registers.read(source))
		)
	}
} catch (_: Exception) {
	errors.GeneralDataTransferException("Store")
}