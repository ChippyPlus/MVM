package internals.instructions.bitwise


import data.registers.enumIdenifiers.SuperRegisterType
import data.registers.enumIdenifiers.SuperRegisterType.R3
import errors
import helpers.fullRegisterRead
import helpers.fullRegisterWrite

/**
 * Performs a bitwise XOR operation on the values in two registers and stores the result in the `R3` register.
 *
 * @param operand1 The [SuperRegisterType] holding the first operand.
 * @param operand2 The [SuperRegisterType] holding the second operand.
 * @throws GeneralBitwiseException If an error occurs during the bitwise XOR operation.
 */
fun Bitwise.xor(operand1: SuperRegisterType, operand2: SuperRegisterType): Unit = try {
    fullRegisterWrite(
        register = R3, value = fullRegisterRead(register = operand1).xor(
            other = fullRegisterRead(register = operand2)
        )
    )
} catch (_: Exception) {
    errors.run { this@run.GeneralBitwiseException(message = "xor") }
}