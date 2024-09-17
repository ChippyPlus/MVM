package internals.instructions.bitwise


import data.registers.enumIdenifiers.SuperRegisterType
import data.registers.enumIdenifiers.SuperRegisterType.R3
import errors
import helpers.fullRegisterRead
import helpers.fullRegisterWrite


fun Bitwise.xor(operand1: SuperRegisterType, operand2: SuperRegisterType): Unit = try {
    fullRegisterWrite(
        register = R3, value = fullRegisterRead(register = operand1).xor(
            other = fullRegisterRead(register = operand2)
        )
    )
} catch (_: Exception) {
    errors.run { this@run.GeneralBitwiseException(message = "xor") }
}