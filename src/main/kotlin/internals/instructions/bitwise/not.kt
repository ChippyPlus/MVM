package internals.instructions.bitwise

import data.registers.RegisterType
import registers

/**
 * Performs a bitwise NOT operation on the values in a register and stores the result in the `R3` register.
 * @param operand1 The [RegisterType] holding operand.
 * @throws GeneralBitwiseException If an error occurs during the bitwise AND operation.
 */
fun Bitwise.not(operand: RegisterType) = call("not") {
	registers.read(operand).inv()
}