package internals.instructions.dataTransfer

import data.registers.enumIdenifiers.SuperRegisterType
import errors
import helpers.fullRegisterWrite

fun DataTransfer.lit(Source: SuperRegisterType, value: Long): Unit = try {
    fullRegisterWrite(
        register = Source,
        value = value
    )

} catch (_: Exception) {
    errors.run { this.GeneralDataTransferException(message = "Lit") }
}