package internals.instructions.dataTransfer

import org.example.data.registers.enumIdenifiers.SuperRegisterType
import org.example.errors
import org.example.helpers.fullRegisterRead
import org.example.helpers.fullRegisterWrite



fun DataTransfer.mov(Source: SuperRegisterType, Destination: SuperRegisterType) = try {
    val value = fullRegisterRead(Source)
    fullRegisterWrite(Destination, value)
} catch (_: Exception) {
    errors.GeneralDataTransferException("mov")
}