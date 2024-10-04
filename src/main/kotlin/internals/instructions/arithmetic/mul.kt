@file:Suppress("RemoveExplicitTypeArguments")

package internals.instructions.arithmetic

import data.registers.enumIdenifiers.ReturnRegisterType.R4
import data.registers.enumIdenifiers.SuperRegisterType
import environment.VMErrors
import errors
import helpers.fullRegisterRead
import returnRegisters

/**
 * Multiplies the values in two registers and stores the result in the `R4` register.
 *
 * @param registerA The [SuperRegisterType] holding the first operand.
 * @param registerB The [SuperRegisterType] holding the second operand.
 * @throws GeneralArithmeticException If an arithmetic error occurs during the multiplication.
 */
fun Arithmetic.mul(registerA: SuperRegisterType, registerB: SuperRegisterType): Unit = try {
    val a: Long = fullRegisterRead(register = registerA)
    val b: Long = fullRegisterRead(register = registerB)
    returnRegisters.run {
        write(
            registers = R4,
            value = a.run {
                times(other = b)
            },
        )
    }
} catch (e: Exception) {
    errors.run<VMErrors, Unit> { this@run.GeneralArithmeticException(message = "mul") }
}