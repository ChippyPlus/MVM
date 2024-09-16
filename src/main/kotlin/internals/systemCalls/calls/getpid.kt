package internals.systemCalls.calls

import internals.systemCalls.SystemCall
import data.registers.enumIdenifiers.SuperRegisterType.R2
import environment.VMErrors
import errors
import helpers.fullRegisterWrite

@Suppress("RemoveExplicitTypeArguments")
fun SystemCall.getPid(): Unit = try {
    fullRegisterWrite(
        register = R2, value = ProcessHandle.current().pid()
    )
} catch (_: Exception) {
    errors.run<VMErrors, Unit> {
        this.SystemCallGeneralException(message = "exit")
    }
}