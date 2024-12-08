package internals.instructions.strings

//@Deprecated("Moved into stdlib functions")
//fun Strings.strcpy(source: RegisterType, destination: RegisterType): Unit = try {
//	registers.write(intelNames[IntelRegisters.ENSF], true.toLong())
//
//	val string: String = helpers.readRegisterString(register = source)
//	val destinationAddress: Long = registers.read(register = destination)
//	helpers.writeStringSpecInMemory(
//		string = string, destinationAddress = destinationAddress
//	)
//} catch (_: Exception) {
//	errors.GeneralStringException(message = "Strcpy")
//}