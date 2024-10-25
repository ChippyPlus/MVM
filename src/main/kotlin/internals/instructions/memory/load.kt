package internals.instructions.memory

import data.memory.MemoryAddress
import data.registers.IntelRegisters
import data.registers.RegisterType
import data.registers.intelNames
import errors
import helpers.toDouble
import internalMemory
import registers

/**
 * Loads a value from memory into a register.
 *
 * @param memoryAddress The memory address to load from.
 * @param destination The destination register to store the loaded value.
 * @throws GeneralMemoryException If an error occurs during the memory load operation.
 */
fun Memory.load(memoryAddress: RegisterType, destination: RegisterType): Unit = try {
	registers.write(
		register = destination,
		value = internalMemory.read(MemoryAddress(registers.read(memoryAddress).toLong())).value!!
	)
	registers.write(intelNames[IntelRegisters.ENSF], true.toDouble())

} catch (_: Exception) {
	errors.GeneralMemoryException("Load")

}