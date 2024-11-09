package internals.instructions.ioAbstractions

import data.registers.IntelRegisters
import data.registers.intelNames
import errors
import helpers.toLong
import registers
import vm

/**
 * Prints the value at the top of the stack to the console.
 *
 * @throws GeneralIoAbstractionsException If an error occurs during the printing operation.
 */
fun IoAbstractions.prints(): Unit = try {
	println(vm.stackOperations.internalStack.peek())
	registers.write(intelNames[IntelRegisters.ENSF], true.toLong())
} catch (_: Exception) {
	errors.GeneralIoAbstractionsException(message = "prints")
}