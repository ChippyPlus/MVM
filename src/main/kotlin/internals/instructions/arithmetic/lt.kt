package internals.instructions.arithmetic

import data.registers.enumIdenifiers.SuperRegisterType
import data.registers.enumIdenifiers.SuperRegisterType.R4
import errors
import helpers.registerRead
import helpers.registerWrite


fun Arithmetic.lt(operand1: SuperRegisterType, operand2: SuperRegisterType) = try {
    if (registerRead(register = operand1) < registerRead(register = operand2)) {
        registerWrite(
            register = R4, value = 0
        )
    } else {
        registerWrite(
            register = R4, value = 1
        )
    }
} catch (e: Exception) {
    errors.run { this@run.GeneralArithmeticException(message = "lt") }
}

