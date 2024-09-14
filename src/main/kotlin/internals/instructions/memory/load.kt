package internals.instructions.memory

import org.example.data.memory.MemoryAddress
import org.example.data.registers.enumIdenifiers.SuperRegisterType
import org.example.errors
import org.example.helpers.fullRegisterWrite
import org.example.internalMemory


fun Memory.load(memoryAddress: MemoryAddress, destination: SuperRegisterType) = try{
    fullRegisterWrite(destination, internalMemory.read(memoryAddress).value!!.toLong())
} catch (_: Exception){
    errors.GeneralMemoryException("load")
}