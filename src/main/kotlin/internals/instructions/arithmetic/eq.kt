package internals.instructions.arithmetic

import org.example.data.registers.enumIdenifiers.SuperRegisterType
import org.example.data.registers.enumIdenifiers.SuperRegisterType.R4
import org.example.errors
import org.example.helpers.fullRegisterRead
import org.example.helpers.fullRegisterWrite


fun Arithmetic.eq(operand1: SuperRegisterType, operand2: SuperRegisterType) = try {
    if (fullRegisterRead(register = operand1) == fullRegisterRead(register = operand2)) {
        fullRegisterWrite(
            register = R4, value = 0
        )
    } else {
        fullRegisterWrite(
            register = R4, value = 1
        )
    }
} catch (e: Exception) {
    errors.run { this@run.GeneralArithmeticException(message = "eq") }
}