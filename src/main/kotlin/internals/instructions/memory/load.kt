package internals.instructions.memory

import data.registers.IntelRegisters
import data.registers.RegisterType
import data.registers.intelNames
import helpers.toLong

/**
 * Loads a value from memory into a register.
 *
 * @param memoryAddress The memory address to load from.
 * @param destination The destination register to store the loaded value.
 * @throws GeneralMemoryException If an error occurs during the memory load operation.
 */
fun Memory.load(memoryAddress: RegisterType, destination: RegisterType): Unit = try {
	registers.write(
		register = destination, value = internalMemory.read(registers.read(memoryAddress))
	)
	registers.write(intelNames[IntelRegisters.ENSF], true.toLong())

} catch (e: Exception) {
	errors.GeneralMemoryException("Load")

}