package internals.instructions.strings

import org.example.data.memory.MemoryAddress
import org.example.data.memory.MemoryValue
import org.example.data.registers.enumIdenifiers.SuperRegisterType
import org.example.errors
import org.example.helpers.fullRegisterRead
import org.example.helpers.readRegisterString
import org.example.internalMemory
import org.example.kvmInternals.instructions.strings.Strings

fun Strings.strcpy(source: SuperRegisterType, destination: SuperRegisterType) {
    val string = readRegisterString(source)
    val destinationAddress = fullRegisterRead(destination)
    val possibleStarts = emptyMap<Long?, Any?>().toMutableMap()

    internalMemory.memory.forEach {
        possibleStarts[it.key.address] = it.value.value
    }
    possibleStarts.filter { it.value == 0 }
    val allocMem = string.length

    for (i in (destinationAddress until (destinationAddress + allocMem))) {
        if (internalMemory.memory[MemoryAddress(i)] != MemoryValue(null)) {
            errors.NotFreeMemoryException(i.toString())
        }
    }

    for ((index, i) in (destinationAddress until (destinationAddress + allocMem)).withIndex()) {
        internalMemory.memory[MemoryAddress(i)] = MemoryValue(string[index].code.toLong())
    }
    internalMemory.memory[MemoryAddress(destinationAddress + allocMem)] = MemoryValue(0)
}