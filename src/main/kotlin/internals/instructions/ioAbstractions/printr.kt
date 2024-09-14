package internals.instructions.ioAbstractions

import org.example.data.registers.enumIdenifiers.SuperRegisterType
import org.example.errors
import org.example.helpers.fullRegisterRead
import org.example.kvmInternals.instructions.ioAbstractions.IoAbstractions

fun IoAbstractions.printr(register: SuperRegisterType) = try {
    println(fullRegisterRead(register))
} catch (_: Exception) {
    errors.GeneralIoAbstractionsException("printr")
}