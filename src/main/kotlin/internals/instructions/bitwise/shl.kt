package internals.instructions.bitwise


import org.example.data.registers.enumIdenifiers.SuperRegisterType
import org.example.errors
import org.example.helpers.fullRegisterRead
import org.example.helpers.fullRegisterWrite


fun Bitwise.shl(operand1: SuperRegisterType, operand2: SuperRegisterType) = try {
    fullRegisterWrite(
        SuperRegisterType.R3, fullRegisterRead(operand1) shl fullRegisterRead(operand2).toInt()
    )
} catch (_: Exception) {
    errors.GeneralBitwiseException("shl")
}