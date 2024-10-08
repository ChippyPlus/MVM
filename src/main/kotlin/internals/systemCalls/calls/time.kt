@file:Suppress("RemoveExplicitTypeArguments")

package internals.systemCalls.calls

import data.registers.enumIdenifiers.SuperRegisterType
import environment.VMErrors
import errors
import helpers.fullRegisterWrite
import internals.systemCalls.SystemCall

/**
 * Gets the current system time in milliseconds.
 *
 * System call number: 14
 */
fun SystemCall.time(): Unit = try {
    fullRegisterWrite(register = SuperRegisterType.R2, value = System.currentTimeMillis())
} catch (_: Exception) {
    errors.run<VMErrors, Unit> {
        this.SystemCallGeneralException(message = "exit")
    }
}