package helpers

import data.memory.MemoryAddress
import data.registers.RegisterType
import internalMemory
import registers

/**
 * Reads a null-terminated string from memory starting at the address stored in the specified register.
 *
 * @param register The register containing the starting memory address of the string.
 * @return The string read from memory.
 */
fun readRegisterString(register: RegisterType): String {
	var index = 0
	var string = ""
	while (true) {
		val byte = internalMemory.read(MemoryAddress(registers.read(register).toLong() + index))
		if (byte.value == 0L) break
		string += byte.value!!.toInt().toChar()
		index++
	}
	return string
}