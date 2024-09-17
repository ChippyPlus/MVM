package internals.instructions.memory

import data.memory.MemoryAddress
import data.registers.enumIdenifiers.SuperRegisterType
import errors
import helpers.fullRegisterWrite
import internalMemory


fun Memory.load(memoryAddress: MemoryAddress, destination: SuperRegisterType): Unit = try {
    fullRegisterWrite(
        register = destination,
        value = internalMemory.read(address = memoryAddress).value!!.toLong()
    )
} catch (_: Exception) {
    errors.run { this@run.GeneralMemoryException(message = "load") }
}