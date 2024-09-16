package internals.instructions.arithmetic

import data.registers.enumIdenifiers.ReturnRegisterType.R4
import data.registers.enumIdenifiers.SuperRegisterType
import environment.VMErrors
import errors
import helpers.fullRegisterRead
import returnRegisters

fun Arithmetic.mod(registerA: SuperRegisterType, registerB: SuperRegisterType): Unit = try {
    val A: Long = fullRegisterRead(register = registerA)
    val B: Long = fullRegisterRead(register = registerB)
    returnRegisters.run {
        write(
            registers = R4,
            value = A.run {
                mod(other = B)
            },
        )
    }
} catch (e: Exception) {
    errors.run<VMErrors, Unit> { this@run.GeneralArithmeticException(message = "mod") }
}