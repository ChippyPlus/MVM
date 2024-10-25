package internals.instructions.strings

import data.memory.MemoryAddress
import data.registers.IntelRegisters
import data.registers.RegisterType
import data.registers.RegisterType.R4
import data.registers.intelNames
import errors
import helpers.toDouble
import internalMemory
import registers

/**
 * Calculates the length of a null-terminated string and stores it in the `R4` register.
 *
 * @param addressRegister The register containing the memory address of the first character of the string.
 * @throws GeneralStringException If an error occurs during the string length calculation.
 */
fun Strings.strlen(addressRegister: RegisterType): Unit = try {
	registers.write(intelNames[IntelRegisters.ENSF], true.toDouble())

	var index: Long = 0L
	while (true) {
		val byte = internalMemory.read(
			address = MemoryAddress(address = registers.read(addressRegister).toLong() + index)
		)
		if (byte.value?.equals(0L) ?: (false)) {
			break
		}
		index++
	}
	registers.write(register = R4, value = index)
} catch (_: Exception) {
	errors.GeneralStringException(message = "strlen")
}