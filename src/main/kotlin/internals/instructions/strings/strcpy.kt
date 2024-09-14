package internals.instructions.strings

import org.example.data.memory.MemoryAddress
import org.example.data.registers.enumIdenifiers.SuperRegisterType
import org.example.errors
import org.example.helpers.fullRegisterRead
import org.example.helpers.readRegisterString
import org.example.helpers.writeStringSpecInMemory

fun Strings.strcpy(source: SuperRegisterType, destination: SuperRegisterType) = try {
    val string = readRegisterString(source)
    val destinationAddress = fullRegisterRead(destination)
    writeStringSpecInMemory(string, MemoryAddress(destinationAddress))
} catch (_: Exception) {
    errors.GeneralStringException("strcpy")
}