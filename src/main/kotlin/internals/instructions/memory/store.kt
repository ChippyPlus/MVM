package internals.instructions.memory

import data.registers.IntelRegisters
import data.registers.RegisterType
import data.registers.intelNames
import helpers.toLong

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
			address = registers.read(destination), value = registers.read(source)
		)
	}
	registers.write(intelNames[IntelRegisters.ENSF], true.toLong())

} catch (_: Exception) {
	errors.GeneralMemoryException("Store")
}