package internals.instructions.strings

/**
 * Calculates the length of a null-terminated string and stores it in the `R4` register.
 *
 * @param addressRegister The register containing the memory address of the first character of the string.
 * @throws GeneralStringException If an error occurs during the string length calculation.
 */
//@Deprecated("Moved into stdlib functions")
//fun Strings.strlen(addressRegister: RegisterType): Unit = try {
//	registers.write(intelNames[IntelRegisters.ENSF], true.toLong())
//
//	var index: Long = 0L
//	while (true) {
//		val byte = internalMemory.read(
//			address = MemoryAddress(address = registers.read(addressRegister) + index)
//		)
//		if (byte.value?.equals(0L) ?: (false)) {
//			break
//		}
//		index++
//	}
//	registers.write(register = R4, value = index)
//} catch (_: Exception) {
//	errors.GeneralStringException(message = "strlen")
//}