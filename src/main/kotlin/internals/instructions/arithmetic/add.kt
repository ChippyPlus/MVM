package internals.instructions.arithmetic

import data.registers.enumIdenifiers.ReturnRegisterType.R4
import data.registers.enumIdenifiers.SuperRegisterType
import environment.VMErrors
import errors
import helpers.registerRead
import returnRegisters

/**
 * Adds the values in two registers and stores the result in the `R4` register.
 *
 * @param registerA The [SuperRegisterType] holding the first operand.
 * @param registerB The [SuperRegisterType] holding the second operand.
 * @throws GeneralArithmeticException If an arithmetic error occurs during the addition.
 */
fun Arithmetic.add(registerA: SuperRegisterType, registerB: SuperRegisterType): Unit = try {
    val a: Long = registerRead(register = registerA)
    val b: Long = registerRead(register = registerB)
    returnRegisters.run {
        write(
            registers = R4,
            value = a.run {
                plus(other = b)
            },
        )
    }
} catch (e: Exception) {
    @Suppress("RemoveExplicitTypeArguments")
    errors.run<VMErrors, Unit> { this@run.GeneralArithmeticException(message = "add") }
}