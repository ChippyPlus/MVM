package internals.systemCalls.calls

import data.registers.enumIdenifiers.SuperRegisterType
import environment.VMErrors
import errors
import helpers.registerRead
import internals.systemCalls.SystemCall
import kotlin.system.exitProcess

/**
 * Terminates the current process with the specified exit status.
 *
 * System call number: 5
 *
 * @param s2 The register containing the exit status code (stored in register S2).
 */
@Suppress("RemoveExplicitTypeArguments")
fun SystemCall.exit(s2: SuperRegisterType): Unit = try {
    val exitCode: Long = registerRead(register = s2)
    exitProcess(status = with<Long, Int>(receiver = exitCode) { return@with this.run<Long, Int>(block = Long::toInt) })
} catch (_: Exception) {
    errors.run<VMErrors, Unit> {
        this.SystemCallGeneralException(message = "exit")
    }
}