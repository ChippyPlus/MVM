package internals.instructions.bitwise

import data.registers.RegisterType

/**
 * Performs a logical right shift operation on the value in the operand register by the amount specified in the shift amount register,
 * and stores the result in the `R3` register.
 *
 * @param operand1 The [RegisterType] holding the operand to be shifted.
 * @param operand2 The [RegisterType] holding the shift amount.
 * @throws GeneralBitwiseException If an error occurs during the right shift operation.
 */

fun Bitwise.shr(operand1: RegisterType, operand2: RegisterType, where: RegisterType) = call("shr", where) {
	registers.read(operand1) shr registers.read(operand2).toInt()
}