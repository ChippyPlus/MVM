package internals.instructions.bitwise


import data.registers.RegisterType
import data.registers.RegisterType.R3
import errors
import registers

/**
 * Performs a logical left shift operation on the value in the operand register by the amount specified in the shift amount register,
 * and stores the result in the `R3` register.
 *
 * @param operand1 The [RegisterType] holding the operand to be shifted.
 * @param operand2 The [RegisterType] holding the shift amount.
 * @throws GeneralBitwiseException If an error occurs during the left shift operation.
 */
fun Bitwise.shl(operand1: RegisterType, operand2: RegisterType): Unit = try {
	registers.write(
		R3, value =
		registers.read(operand1) and registers.read(operand2)

	)
} catch (_: Exception) {
	errors.run { this@run.GeneralBitwiseException(message = "shl") }
}