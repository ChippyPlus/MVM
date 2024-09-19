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
 * Divides the value in registerA by registerB and stores the result in the `R1` register.
 *
 * @param registerA The [SuperRegisterType] holding the dividend.
 * @param registerB The [SuperRegisterType] holding the divisor.
 * @throws GeneralArithmeticException If an arithmetic error occurs during the division (e.g. division by zero).
 */
fun Floats.fDiv(registerA: FloatingRegisterType, registerB: FloatingRegisterType): Unit = try {
    val A: Double = floatingRegisterRead(registerA)
    val B: Double = floatingRegisterRead(registerB)
    returnRegisters.write(ReturnRegisterType.R1, A / B)

} catch (e: Exception) {
    @Suppress("RemoveExplicitTypeArguments")
    errors.run<VMErrors, Unit> { this@run.GeneralArithmeticException(message = "fDiv") }
}