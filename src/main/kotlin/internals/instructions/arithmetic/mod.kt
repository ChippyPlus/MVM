package internals.instructions.arithmetic

import data.registers.enumIdenifiers.ReturnRegisterType.R4
import data.registers.enumIdenifiers.SuperRegisterType
import environment.VMErrors
import errors
import helpers.fullRegisterRead
import returnRegisters

/**
 * Calculates the remainder of the division of registerA by registerB and stores the result in the `R4` register.
 *
 * @param registerA The [SuperRegisterType] holding the dividend.
 * @param registerB The [SuperRegisterType] holding the divisor.
 * @throws GeneralArithmeticException If an arithmetic error occurs during the modulo operation.
 */
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
    @Suppress("RemoveExplicitTypeArguments")
    errors.run<VMErrors, Unit> { this@run.GeneralArithmeticException(message = "mod") }
}