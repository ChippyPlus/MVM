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
 * Adds the values in two registers and stores the result in the `R1` register.
 *
 * @param registerA The [SuperRegisterType] holding the first operand.
 * @param registerB The [SuperRegisterType] holding the second operand.
 * @throws GeneralArithmeticException If an arithmetic error occurs during the addition.
 */
fun Floats.fAdd(registerA: FloatingRegisterType, registerB: FloatingRegisterType): Unit = try {
    val A: Double = floatingRegisterRead(registerA)
    val B: Double = floatingRegisterRead(registerB)
    returnRegisters.write(ReturnRegisterType.R1, A + B)

} catch (e: Exception) {
    @Suppress("RemoveExplicitTypeArguments")
    errors.run<VMErrors, Unit> { this@run.GeneralArithmeticException(message = "fAdd") }
}