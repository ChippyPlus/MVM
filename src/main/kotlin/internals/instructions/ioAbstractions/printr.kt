package internals.instructions.ioAbstractions

import data.registers.IntelRegisters
import data.registers.RegisterType
import data.registers.intelNames
import errors
import helpers.toLong
import registers


/**
 * Prints the value at the top of the stack to the console.
 *
 * @throws GeneralIoAbstractionsException If an error occurs during the printing operation.
 */
fun IoAbstractions.printr(register: RegisterType): Unit = try {
	println(message = registers.read(register = register))
	registers.write(intelNames[IntelRegisters.ENSF], true.toLong())

} catch (_: Exception) {
	errors.GeneralIoAbstractionsException(message = "printr")
}