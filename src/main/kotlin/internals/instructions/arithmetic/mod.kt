package internals.instructions.arithmetic

import data.registers.enumIdenifiers.ReturnRegisterType.R4
import data.registers.enumIdenifiers.SuperRegisterType
import errors
import helpers.registerRead
import returnRegisters

/**
 * Calculates the remainder of the division of registerA by registerB and stores the result in the `R4` register.
 *
 * @param registerA The [SuperRegisterType] holding the dividend.
 * @param registerB The [SuperRegisterType] holding the divisor.
 * @throws GeneralArithmeticException If an arithmetic error occurs during the modulo operation.
 */
fun Arithmetic.mod(registerA: SuperRegisterType, registerB: SuperRegisterType): Unit = try {
    val a: Long = registerRead(register = registerA)
    val b: Long = registerRead(register = registerB)
    returnRegisters.run {
        write(
            registers = R4,
            value = a.run {
                mod(other = b)
            },
        )
    }
} catch (e: Exception) {
    errors.run { this@run.GeneralArithmeticException(message = "mod") }
}