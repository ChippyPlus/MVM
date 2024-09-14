package internals.instructions.dataTransfer

import org.example.data.registers.enumIdenifiers.SuperRegisterType
import org.example.errors
import org.example.helpers.fullRegisterRead
import org.example.helpers.fullRegisterWrite

fun DataTransfer.cpy(register1: SuperRegisterType, register2: SuperRegisterType) = try {
    val a = fullRegisterRead(register1)
    fullRegisterWrite(register2, a)
} catch (_: Exception) {
    errors.GeneralDataTransferException("Cpy")
}