package internals.instructions.ioAbstractions

import data.registers.RegisterType
import errors
import registers


/**
 * Prints the value at the top of the stack to the console.
 *
 * @throws GeneralIoAbstractionsException If an error occurs during the printing operation.
 */
fun IoAbstractions.printr(register: RegisterType): Unit = try {
    println(message = registers.read(register = register))
} catch (_: Exception) {
    errors.run { GeneralIoAbstractionsException(message = "printr") }
}