package internals.instructions.strings

import data.memory.MemoryAddress
import data.registers.IntelRegisters
import data.registers.RegisterType
import data.registers.intelNames
import errors
import helpers.readRegisterString
import helpers.toLong
import helpers.writeStringSpecInMemory
import registers

/**
 * Copies a string from one memory location to another.
 *
 * @param source The register containing the memory address of the source string.
 * @param destination The register containing the memory address of the destination location.
 * @throws GeneralStringException If an error occurs during the string copy.
 */

fun Strings.strcpy(source: RegisterType, destination: RegisterType): Unit = try {
    registers.write(intelNames[IntelRegisters.ENSF], true.toLong())

    val string: String = readRegisterString(register = source)
    val destinationAddress: Long = registers.read(register = destination)
    writeStringSpecInMemory(
        string = string, destinationAddress = MemoryAddress(address = destinationAddress)
    )
} catch (_: Exception) {
    errors.GeneralStringException(message = "Strcpy")
}