package internals.instructions.arithmetic

import data.registers.enumIdenifiers.SuperRegisterType
import data.registers.enumIdenifiers.SuperRegisterType.R4
import errors
import helpers.fullRegisterRead
import helpers.fullRegisterWrite


fun Arithmetic.lt(operand1: SuperRegisterType, operand2: SuperRegisterType) = try {
    if (fullRegisterRead(register = operand1) < fullRegisterRead(register = operand2)) {
        fullRegisterWrite(
            register = R4, value = 0
        )
    } else {
        fullRegisterWrite(
            register = R4, value = 1
        )
    }
} catch (e: Exception) {
    errors.run { this@run.GeneralArithmeticException(message = "lt") }
}

