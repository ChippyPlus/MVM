package internals.instructions.bitwise

import data.registers.RegisterType
import registers

/**
 * Performs a bitwise OR operation on the values in two registers and stores the result in the `R3` register.
 *
 * @param operand1 The [RegisterType] holding the first operand.
 * @param operand2 The [RegisterType] holding the second operand.
 * @throws GeneralBitwiseException If an error occurs during the bitwise OR operation.
 */
fun Bitwise.or(operand1: RegisterType, operand2: RegisterType) = call("or") {
	registers.read(operand1) or registers.read(operand2)

}