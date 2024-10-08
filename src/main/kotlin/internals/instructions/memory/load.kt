package internals.instructions.memory

import data.memory.MemoryAddress
import data.registers.enumIdenifiers.SuperRegisterType
import errors
import helpers.fullRegisterRead
import helpers.fullRegisterWrite
import internalMemory

/**
 * Loads a value from memory into a register.
 *
 * @param memoryAddress The memory address to load from.
 * @param destination The destination register to store the loaded value.
 * @throws GeneralMemoryException If an error occurs during the memory load operation.
 */
fun Memory.load(memoryAddress: SuperRegisterType, destination: SuperRegisterType): Unit = try {
    fullRegisterWrite(
        register = destination, value = internalMemory.read(MemoryAddress(fullRegisterRead(memoryAddress))).value!!
    )
} catch (_: Exception) {
    errors.run { this@run.GeneralMemoryException(message = "load") }
}