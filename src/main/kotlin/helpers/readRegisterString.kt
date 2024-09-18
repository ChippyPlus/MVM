package helpers

import data.memory.MemoryAddress
import data.registers.enumIdenifiers.SuperRegisterType
import internalMemory

/**
 * Reads a null-terminated string from memory starting at the address stored in the specified register.
 *
 * @param register The register containing the starting memory address of the string.
 * @return The string read from memory.
 */
fun readRegisterString(register: SuperRegisterType): String {
    var index = 0
    var string = ""
    while (true) {
        val byte = internalMemory.read(MemoryAddress(fullRegisterRead(register) + index))
        if (byte.value == 0L) break
        string += byte.value!!.toInt().toChar()
        index++
    }
    return string
}