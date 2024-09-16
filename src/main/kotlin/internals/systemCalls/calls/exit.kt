package internals.systemCalls.calls

import internals.systemCalls.SystemCall
import org.example.data.registers.enumIdenifiers.SuperRegisterType
import org.example.environment.VMErrors
import org.example.errors
import org.example.helpers.fullRegisterRead
import kotlin.system.exitProcess

@Suppress("RemoveExplicitTypeArguments")
fun SystemCall.exit(s2: SuperRegisterType): Unit = try {
    val exitCode: Long = fullRegisterRead(register = s2)
    exitProcess(status = with<Long, Int>(receiver = exitCode) { return@with this.run<Long, Int>(block = Long::toInt) })
} catch (_: Exception) {
    errors.run<VMErrors, Unit> {
        this.SystemCallGeneralException(message = "exit")
    }
}