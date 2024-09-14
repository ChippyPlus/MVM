package internals.instructions.strings

import org.example.data.memory.MemoryAddress
import org.example.data.registers.enumIdenifiers.SuperRegisterType
import org.example.helpers.fullRegisterRead
import org.example.helpers.readRegisterString
import org.example.helpers.writeStringSpecInMemory
import org.example.kvmInternals.instructions.strings.Strings

fun Strings.strcpy(source: SuperRegisterType, destination: SuperRegisterType) {
    val string = readRegisterString(source)
    val destinationAddress = fullRegisterRead(destination)
    writeStringSpecInMemory(string, MemoryAddress(destinationAddress))
}