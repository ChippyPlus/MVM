package internals.instructions.floats.arithmetic

import data.registers.enumIdenifiers.FloatingRegisterType
import data.registers.enumIdenifiers.ReturnRegisterType
import data.registers.enumIdenifiers.SuperRegisterType
import environment.VMErrors
import errors
import helpers.floating.floatingRegisterRead
import internals.instructions.floats.Floats
import returnRegisters

/**
 * Subtracts the value in registerB from registerA and stores the result in the `R4` register.
 *
 * @param registerA The [SuperRegisterType] holding the minuend.
 * @param registerB The [SuperRegisterType] holding the subtrahend.
 * @throws GeneralArithmeticException If an arithmetic error occurs during the subtraction.
 */

fun Floats.fSub(registerA: FloatingRegisterType, registerB: FloatingRegisterType): Unit = try {
    val A: Double = floatingRegisterRead(registerA)
    val B: Double = floatingRegisterRead(registerB)
    returnRegisters.write(ReturnRegisterType.R1, A - B)

} catch (e: Exception) {
    @Suppress("RemoveExplicitTypeArguments")
    errors.run<VMErrors, Unit> { this@run.GeneralArithmeticException(message = "fSub") }
}