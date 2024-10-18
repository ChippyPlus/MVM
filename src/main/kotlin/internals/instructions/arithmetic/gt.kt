package internals.instructions.arithmetic

import data.registers.RegisterType
import data.registers.RegisterType.R4
import errors
import registers


fun Arithmetic.gt(operand1: RegisterType, operand2: RegisterType) = try {
    if (registers.read(register = operand1) > registers.read(register = operand2)) {
        registers.write(
            register = R4, value = 0
        )
    } else {
        registers.write(
            register = R4, value = 1
        )
    }
} catch (e: Exception) {
    errors.run { this@run.GeneralArithmeticException(message = "gt") }
}