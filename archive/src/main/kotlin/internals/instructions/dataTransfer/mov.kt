package internals.instructions.dataTransfer

import data.registers.RegisterType
import errors
import registers

/**
 * Moves the value from the source register to the destination register.
 *
 * @param source The source register.
 * @param destination The destination register.
 * @throws GeneralDataTransferException If an error occurs during the move operation.
 */
fun DataTransfer.mov(source: RegisterType, destination: RegisterType): Unit = try {

	val value: Long = registers.read(source)
	registers.write(register = destination, value = value)
} catch (_: Exception) {
	errors.run {
		this@run.GeneralDataTransferException(message = "Mov")
	}
}