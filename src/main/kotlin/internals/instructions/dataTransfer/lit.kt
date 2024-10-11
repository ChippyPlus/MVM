package internals.instructions.dataTransfer

import data.registers.enumIdenifiers.SuperRegisterType
import errors
import helpers.registerWrite

/**
 * Loads a literal value into a register.
 *
 * @param source The destination register.
 * @param value The literal value to load.
 * @throws GeneralDataTransferException If an error occurs during the load operation.
 */

fun DataTransfer.lit(source: SuperRegisterType, value: Long): Unit = try {
    registerWrite(
        register = source,
        value = value
    )

} catch (_: Exception) {
    errors.run { this.GeneralDataTransferException(message = "Lit") }
}