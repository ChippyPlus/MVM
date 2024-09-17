package internals.instructions.strings

import data.memory.MemoryAddress
import data.registers.enumIdenifiers.SuperRegisterType
import errors
import helpers.fullRegisterRead
import helpers.readRegisterString
import helpers.writeStringSpecInMemory

fun Strings.strcpy(source: SuperRegisterType, destination: SuperRegisterType): Unit = try {
    val string: String = readRegisterString(register = source)
    val destinationAddress: Long = fullRegisterRead(register = destination)
    writeStringSpecInMemory(
        string = string, destinationAddress = MemoryAddress(address = destinationAddress)
    )
} catch (_: Exception) {
    with(receiver = errors) { this@with.GeneralStringException(message = "strcpy") }
}