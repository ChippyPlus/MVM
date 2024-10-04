package internals.instructions.arithmetic

import data.registers.enumIdenifiers.ReturnRegisterType.R4
import data.registers.enumIdenifiers.SuperRegisterType
import environment.VMErrors
import errors
import helpers.fullRegisterRead
import returnRegisters

/**
 * Subtracts the value in registerB from registerA and stores the result in the `R4` register.
 *
 * @param registerA The [SuperRegisterType] holding the minuend.
 * @param registerB The [SuperRegisterType] holding the subtrahend.
 * @throws GeneralArithmeticException If an arithmetic error occurs during the subtraction.
 */

fun Arithmetic.sub(registerA: SuperRegisterType, registerB: SuperRegisterType): Unit = try {
    val a: Long = fullRegisterRead(register = registerA)
    val b: Long = fullRegisterRead(register = registerB)
    returnRegisters.run {
        write(
            registers = R4,
            value = a.run {
                minus(other = b)
            },
        )
    }
} catch (e: Exception) {
    @Suppress("RemoveExplicitTypeArguments")
    errors.run<VMErrors, Unit> { this@run.GeneralArithmeticException(message = "sub") }
}