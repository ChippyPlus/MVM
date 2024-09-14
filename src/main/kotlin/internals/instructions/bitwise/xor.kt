package internals.instructions.bitwise


import org.example.data.registers.enumIdenifiers.SuperRegisterType
import org.example.errors
import org.example.helpers.fullRegisterRead
import org.example.helpers.fullRegisterWrite


fun Bitwise.xor(operand1: SuperRegisterType, operand2: SuperRegisterType) = try {
    fullRegisterWrite(
        SuperRegisterType.R3,
        fullRegisterRead(operand1) xor fullRegisterRead(operand2)
    )
} catch (_: Exception) {
    errors.GeneralBitwiseException("xor")
}