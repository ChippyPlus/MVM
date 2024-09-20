package internals.instructions.floats.arithmetic

import data.registers.enumIdenifiers.FloatingRegisterType
import data.registers.enumIdenifiers.ReturnRegisterType.R4
import data.registers.enumIdenifiers.SuperRegisterType
import environment.VMErrors
import errors
import helpers.floating.floatingRegisterRead
import internals.instructions.floats.Floats
import returnRegisters

/**
 * Calculates the remainder of the division of registerA by registerB and stores the result in the `R1` register.
 *
 * @param registerA The [SuperRegisterType] holding the dividend.
 * @param registerB The [SuperRegisterType] holding the divisor.
 * @throws GeneralArithmeticException If an arithmetic error occurs during the modulo operation.
 */
fun Floats.fMod(registerA: FloatingRegisterType, registerB: FloatingRegisterType): Unit = try {
    val A: Double = floatingRegisterRead(register = registerA)
    val B: Double = floatingRegisterRead(register = registerB)
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
    errors.run<VMErrors, Unit> { this@run.GeneralArithmeticException(message = "fMod") }
}