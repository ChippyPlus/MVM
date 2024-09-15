package internals.instructions.memory

import org.example.data.memory.InternalMemory
import org.example.data.memory.MemoryAddress
import org.example.data.memory.MemoryValue
import org.example.data.registers.enumIdenifiers.SuperRegisterType
import org.example.environment.VMErrors
import org.example.errors
import org.example.helpers.fullRegisterRead
import org.example.internalMemory


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