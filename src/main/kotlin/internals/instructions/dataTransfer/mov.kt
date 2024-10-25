package internals.instructions.dataTransfer

import data.registers.RegisterType
import registers

/**
 * Moves the value from the source register to the destination register.
 *
 * @param source The source register.
 * @param destination The destination register.
 * @throws GeneralDataTransferException If an error occurs during the move operation.
 */
fun DataTransfer.mov(source: RegisterType, destination: RegisterType) = call("mov") {
	val value: Long = registers.read(source).toLong()
	registers.write(register = destination, value = value)
}