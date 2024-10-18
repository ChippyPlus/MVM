@file:Suppress("RemoveExplicitTypeArguments")

package internals.systemCalls.calls

import data.registers.RegisterType
import environment.VMErrors
import errors
import internals.systemCalls.SystemCall
import registers

/**
 * Gets the current system time in milliseconds.
 *
 * System call number: 14
 */
fun SystemCall.time(): Unit = try {
	registers.write(register = RegisterType.R2, value = System.currentTimeMillis())
} catch (_: Exception) {
	errors.run<VMErrors, Unit> {
		this.SystemCallGeneralException(message = "exit")
	}
}