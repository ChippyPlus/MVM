package internals.instructions.ioAbstractions

import org.example.data.registers.enumIdenifiers.SuperRegisterType
import org.example.errors
import org.example.helpers.fullRegisterRead

fun IoAbstractions.printr(register: SuperRegisterType): Unit = try {
    println(message = fullRegisterRead(register = register))
} catch (_: Exception) {
    errors.run { GeneralIoAbstractionsException(message = "printr") }
}