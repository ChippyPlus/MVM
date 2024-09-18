package internals.instructions.dataTransfer

import data.registers.enumIdenifiers.SuperRegisterType
import errors
import helpers.fullRegisterWrite

/**
 * Loads a literal value into a register.
 *
 * @param Source The destination register.
 * @param value The literal value to load.
 * @throws GeneralDataTransferException If an error occurs during the load operation.
 */

fun DataTransfer.lit(Source: SuperRegisterType, value: Long): Unit = try {
    fullRegisterWrite(
        register = Source,
        value = value
    )

} catch (_: Exception) {
    errors.run { this.GeneralDataTransferException(message = "Lit") }
}