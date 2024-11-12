package internals.instructions.dataTransfer

import data.registers.RegisterType

/**
 * Copies the value from one register to another.
 *
 * @param register1 The source register.
 * @param register2 The destination register.
 * @throws GeneralDataTransferException If an error occurs during the copy operation.
 */
fun DataTransfer.cpy(register1: RegisterType, register2: RegisterType) = call("cpy") {
	val value: Long = registers.read(register1)
	registers.write(register = register2, value = value)
}