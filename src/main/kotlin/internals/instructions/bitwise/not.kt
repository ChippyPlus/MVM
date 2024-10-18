package internals.instructions.bitwise

import data.registers.RegisterType
import data.registers.RegisterType.R3
import errors
import registers

/**
 * Performs a bitwise NOT operation on the values in a register and stores the result in the `R3` register.
 * @param operand1 The [RegisterType] holding operand.
 * @throws GeneralBitwiseException If an error occurs during the bitwise AND operation.
 */
fun Bitwise.not(operand: RegisterType) = try {
    registers.write(
        R3, value =
        registers.read(operand).inv()

    )

} catch (_: Exception) {
    with(errors) {
        this@with.GeneralBitwiseException("not")
    }
}