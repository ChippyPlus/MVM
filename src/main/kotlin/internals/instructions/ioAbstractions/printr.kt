package internals.instructions.ioAbstractions

import data.registers.IntelRegisters
import data.registers.RegisterType
import data.registers.intelNames
import helpers.toLong


/**
 * Prints the value at the top of the stack to the console.
 *
 * @throws GeneralIoAbstractionsException If an error occurs during the printing operation.
 */
fun IoAbstractions.printr(register: RegisterType) {
	println(message = registers.read(register = register))
	registers.write(intelNames[IntelRegisters.ENSF], true.toLong())
}