package internals.instructions.ioAbstractions

import data.registers.enumIdenifiers.SuperRegisterType
import errors
import helpers.fullRegisterRead

fun IoAbstractions.printr(register: SuperRegisterType): Unit = try {
    println(message = fullRegisterRead(register = register))
} catch (_: Exception) {
    errors.run { GeneralIoAbstractionsException(message = "printr") }
}