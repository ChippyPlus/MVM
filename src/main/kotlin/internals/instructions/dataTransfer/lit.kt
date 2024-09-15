package internals.instructions.dataTransfer

import org.example.data.registers.enumIdenifiers.SuperRegisterType
import org.example.errors
import org.example.helpers.fullRegisterWrite

fun DataTransfer.lit(Source: SuperRegisterType, value: Long): Unit = try {
    fullRegisterWrite(
        register = Source,
        value = value
    )

} catch (_: Exception) {
    errors.run { this.GeneralDataTransferException(message = "Lit") }
}