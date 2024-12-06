package internals.instructions.bitwise


import data.registers.RegisterType

/**
 * Performs a logical left shift operation on the value in the operand register by the amount specified in the shift amount register,
 * and stores the result in the `R3` register.
 *
 * @param operand1 The [RegisterType] holding the operand to be shifted.
 * @param operand2 The [RegisterType] holding the shift amount.
 * @throws GeneralBitwiseException If an error occurs during the left shift operation.
 */
fun Bitwise.shl(operand1: RegisterType, operand2: RegisterType, where: RegisterType) = call("shl", where) {
	registers.read(operand1) shl registers.read(operand2).toInt()

}