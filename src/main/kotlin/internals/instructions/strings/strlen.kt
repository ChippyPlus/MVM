package internals.instructions.strings

import data.memory.MemoryAddress
import data.registers.enumIdenifiers.SuperRegisterType
import data.registers.enumIdenifiers.SuperRegisterType.R4
import environment.VMErrors
import errors
import helpers.fullRegisterRead
import helpers.fullRegisterWrite
import internalMemory

/**
 * Calculates the length of a null-terminated string and stores it in the `R4` register.
 *
 * @param addressRegister The register containing the memory address of the first character of the string.
 * @throws GeneralStringException If an error occurs during the string length calculation.
 */
fun Strings.strlen(addressRegister: SuperRegisterType): Unit = try {
    var index: Long = 0L
    while (true) {
        val byte = internalMemory.read(
            address = MemoryAddress(address = fullRegisterRead(addressRegister) + index)
        )
        if (byte.value?.equals(0L) ?: (false)) {
            break
        }
        index ++
    }
    fullRegisterWrite(register = R4, value = index)
} catch (_: Exception) {
    @Suppress("RemoveExplicitTypeArguments") errors.run<VMErrors, Unit> { this.GeneralStringException(message = "strlen") }
}