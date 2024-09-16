package internals.systemCalls.calls

import internals.systemCalls.SystemCall
import org.example.data.registers.enumIdenifiers.SuperRegisterType.R2
import org.example.environment.VMErrors
import org.example.errors
import org.example.helpers.fullRegisterWrite

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