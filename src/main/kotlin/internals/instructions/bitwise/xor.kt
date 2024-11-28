package internals.instructions.bitwise


import data.registers.RegisterType

/**
 * Performs a bitwise XOR operation on the values in two registers and stores the result in the `R3` register.
 *
 * @param operand1 The [RegisterType] holding the first operand.
 * @param operand2 The [RegisterType] holding the second operand.
 * @throws GeneralBitwiseException If an error occurs during the bitwise XOR operation.
 */
fun Bitwise.xor(operand1: RegisterType, operand2: RegisterType, where: RegisterType) = call("xor", where) {
	registers.read(operand1) xor registers.read(operand2)

}