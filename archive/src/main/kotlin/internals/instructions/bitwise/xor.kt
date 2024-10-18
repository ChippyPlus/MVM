package internals.instructions.bitwise


import data.registers.RegisterType
import data.registers.RegisterType.R3
import errors
import registers

/**
 * Performs a bitwise XOR operation on the values in two registers and stores the result in the `R3` register.
 *
 * @param operand1 The [RegisterType] holding the first operand.
 * @param operand2 The [RegisterType] holding the second operand.
 * @throws GeneralBitwiseException If an error occurs during the bitwise XOR operation.
 */
fun Bitwise.xor(operand1: RegisterType, operand2: RegisterType): Unit = try {
	registers.write(
		R3, value =
		registers.read(operand1) xor registers.read(operand2)

	)
} catch (_: Exception) {
	errors.run { this@run.GeneralBitwiseException(message = "xor") }
}