package internals.instructions.dataTransfer

import data.registers.RegisterType
import errors
import registers

/**
 * Loads a literal value into a register.
 *
 * @param source The destination register.
 * @param value The literal value to load.
 * @throws GeneralDataTransferException If an error occurs during the load operation.
 */

fun DataTransfer.lit(source: RegisterType, value: Long): Unit = try {
	registers.write(
		register = source, value = value
	)

} catch (_: Exception) {
	errors.run { this.GeneralDataTransferException(message = "Lit") }
}