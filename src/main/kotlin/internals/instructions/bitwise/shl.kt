package internals.instructions.bitwise


import data.registers.enumIdenifiers.SuperRegisterType
import data.registers.enumIdenifiers.SuperRegisterType.R3
import errors
import helpers.registerRead
import helpers.registerWrite

/**
 * Performs a logical left shift operation on the value in the operand register by the amount specified in the shift amount register,
 * and stores the result in the `R3` register.
 *
 * @param operand1 The [SuperRegisterType] holding the operand to be shifted.
 * @param operand2 The [SuperRegisterType] holding the shift amount.
 * @throws GeneralBitwiseException If an error occurs during the left shift operation.
 */
fun Bitwise.shl(operand1: SuperRegisterType, operand2: SuperRegisterType): Unit = try {
    registerWrite(
        register = R3, value = (registerRead(register = operand1) as Long).shl(
            bitCount = registerRead(
                register = operand2
            ).toInt()
        )
    )
} catch (_: Exception) {
    errors.run { this@run.GeneralBitwiseException(message = "shl") }
}