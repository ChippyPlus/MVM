package internals.instructions.dataTransfer

import org.example.data.registers.enumIdenifiers.SuperRegisterType
import org.example.errors
import org.example.helpers.fullRegisterWrite

fun DataTransfer.lit(Source: SuperRegisterType, value: Long) = try {
    fullRegisterWrite(Source, value)
} catch (_: Exception) {
    errors.GeneralDataTransferException("Lit")
}