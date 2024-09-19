package internals.instructions.ioAbstractions

import data.registers.enumIdenifiers.SuperRegisterType
import errors
import helpers.registerRead


/**
 * Prints the value at the top of the stack to the console.
 *
 * @throws GeneralIoAbstractionsException If an error occurs during the printing operation.
 */
fun IoAbstractions.printr(register: SuperRegisterType): Unit = try {
    println(message = registerRead(register = register))
} catch (_: Exception) {
    errors.run { GeneralIoAbstractionsException(message = "printr") }
}