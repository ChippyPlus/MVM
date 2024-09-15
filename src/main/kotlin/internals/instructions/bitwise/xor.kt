package internals.instructions.bitwise


import org.example.data.registers.enumIdenifiers.SuperRegisterType
import org.example.data.registers.enumIdenifiers.SuperRegisterType.R3
import org.example.errors
import org.example.helpers.fullRegisterRead
import org.example.helpers.fullRegisterWrite


fun Bitwise.xor(operand1: SuperRegisterType, operand2: SuperRegisterType): Unit = try {
    fullRegisterWrite(
        register = R3, value = fullRegisterRead(register = operand1).xor(
            other = fullRegisterRead(register = operand2)
        )
    )
} catch (_: Exception) {
    errors.run { this@run.GeneralBitwiseException(message = "xor") }
}