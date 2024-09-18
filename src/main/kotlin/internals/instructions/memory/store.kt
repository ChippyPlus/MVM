package internals.instructions.memory

import data.memory.InternalMemory
import data.memory.MemoryAddress
import data.memory.MemoryValue
import data.registers.enumIdenifiers.SuperRegisterType
import environment.VMErrors
import errors
import helpers.fullRegisterRead
import internalMemory

/**
 * Stores a value from a register into memory.
 *
 * @param source The source register containing the value to store.
 * @param destination The register holding the memory address to store the value.
 * @throws GeneralMemoryException If an error occurs during the memory store operation.
 */
fun Memory.store(source: SuperRegisterType, destination: SuperRegisterType) = try {
    @Suppress("RemoveExplicitTypeArguments")
    with<InternalMemory, Unit>(receiver = internalMemory) {
        @Suppress("UNNECESSARY_NOT_NULL_ASSERTION")
        this@with!!.write(
            address = MemoryAddress(fullRegisterRead(destination)),
            value = MemoryValue(fullRegisterRead(source))
        )
    }
} catch (_: Exception) {
    @Suppress("RemoveExplicitTypeArguments")
    with<VMErrors, Unit>(receiver = errors) { this@with.GeneralMemoryException("store") }
}