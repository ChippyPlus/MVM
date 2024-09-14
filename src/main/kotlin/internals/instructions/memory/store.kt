package internals.instructions.memory

import org.example.data.memory.MemoryAddress
import org.example.data.memory.MemoryValue
import org.example.data.registers.enumIdenifiers.SuperRegisterType
import org.example.errors
import org.example.helpers.fullRegisterRead
import org.example.internalMemory


fun Memory.store(source: SuperRegisterType, destination: SuperRegisterType) = try {
    internalMemory.write(MemoryAddress(fullRegisterRead( destination)), MemoryValue(fullRegisterRead(source)))
} catch (_: Exception) {
    errors.GeneralMemoryException("store")
}