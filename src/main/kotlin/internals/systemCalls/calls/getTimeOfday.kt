@file:Suppress("RemoveExplicitTypeArguments")

package internals.systemCalls.calls

import internals.systemCalls.SystemCall
import org.example.data.registers.enumIdenifiers.SuperRegisterType
import org.example.environment.VMErrors
import org.example.errors
import org.example.helpers.fullRegisterWrite

fun SystemCall.getTimeOfday(): Unit = try {
    fullRegisterWrite(register = SuperRegisterType.R2, value = System.currentTimeMillis())
} catch (_: Exception) {
    errors.run<VMErrors, Unit> {
        this.SystemCallGeneralException(message = "exit")
    }
}