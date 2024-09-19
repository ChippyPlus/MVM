package internals.instructions.arithmetic

import data.registers.enumIdenifiers.ReturnRegisterType.R4
import data.registers.enumIdenifiers.SuperRegisterType
import environment.VMErrors
import errors
import helpers.registerRead
import returnRegisters

/**
 * Divides the value in registerA by registerB and stores the result in the `R4` register.
 *
 * @param registerA The [SuperRegisterType] holding the dividend.
 * @param registerB The [SuperRegisterType] holding the divisor.
 * @throws GeneralArithmeticException If an arithmetic error occurs during the division (e.g., division by zero).
 */
fun Arithmetic.div(registerA: SuperRegisterType, registerB: SuperRegisterType): Unit = try {
    val A: Long = registerRead(register = registerA) as Long
    val B: Long = registerRead(register = registerB) as Long
    returnRegisters.run {
        write(
            registers = R4,
            value = A.run {
                div(other = B)
            },
        )
    }
} catch (e: Exception) {
    @Suppress("RemoveExplicitTypeArguments")
    errors.run<VMErrors, Unit> { this@run.GeneralArithmeticException(message = "div") }
}