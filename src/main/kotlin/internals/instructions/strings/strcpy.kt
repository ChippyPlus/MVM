package internals.instructions.strings

import org.example.data.memory.MemoryAddress
import org.example.data.registers.enumIdenifiers.SuperRegisterType
import org.example.errors
import org.example.helpers.fullRegisterRead
import org.example.helpers.readRegisterString
import org.example.helpers.writeStringSpecInMemory

fun Strings.strcpy(source: SuperRegisterType, destination: SuperRegisterType): Unit = try {
    val string: String = readRegisterString(register = source)
    val destinationAddress: Long = fullRegisterRead(register = destination)
    writeStringSpecInMemory(
        string = string, destinationAddress = MemoryAddress(address = destinationAddress)
    )
} catch (_: Exception) {
    with(receiver = errors) { this@with.GeneralStringException(message = "strcpy") }
}