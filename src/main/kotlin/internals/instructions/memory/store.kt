package internals.instructions.memory

import data.memory.InternalMemory
import data.memory.MemoryAddress
import data.memory.MemoryValue
import data.registers.enumIdenifiers.SuperRegisterType
import environment.VMErrors
import errors
import helpers.fullRegisterRead
import internalMemory


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