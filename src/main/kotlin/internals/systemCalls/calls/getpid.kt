package internals.systemCalls.calls

import data.registers.enumIdenifiers.SuperRegisterType.R2
import environment.VMErrors
import errors
import helpers.fullRegisterWrite
import internals.systemCalls.SystemCall

/**
 * Retrieves the process ID of the current process.
 *
 * System call number: 16
 */
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