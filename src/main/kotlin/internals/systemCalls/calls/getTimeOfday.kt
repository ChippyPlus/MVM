@file:Suppress("RemoveExplicitTypeArguments")

package internals.systemCalls.calls

import internals.systemCalls.SystemCall
import data.registers.enumIdenifiers.SuperRegisterType
import environment.VMErrors
import errors
import helpers.fullRegisterWrite

fun SystemCall.getTimeOfday(): Unit = try {
    fullRegisterWrite(register = SuperRegisterType.R2, value = System.currentTimeMillis())
} catch (_: Exception) {
    errors.run<VMErrors, Unit> {
        this.SystemCallGeneralException(message = "exit")
    }
}