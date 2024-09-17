package internals.systemCalls.calls

import data.registers.enumIdenifiers.SuperRegisterType
import environment.VMErrors
import errors
import helpers.fullRegisterRead
import internals.systemCalls.SystemCall
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