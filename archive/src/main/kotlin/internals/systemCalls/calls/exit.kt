package internals.systemCalls.calls

import data.registers.RegisterType
import environment.VMErrors
import errors
import internals.systemCalls.SystemCall
import registers
import kotlin.system.exitProcess

/**
 * Terminates the current process with the specified exit status.
 *
 * System call number: 5
 *
 * @param s2 The register containing the exit status code (stored in register S1).
 */
@Suppress("RemoveExplicitTypeArguments")
fun SystemCall.exit(s2: RegisterType): Unit = try {
	val exitCode: Long = registers.read(register = s2)
	exitProcess(status = with<Long, Int>(receiver = exitCode) { return@with this.run<Long, Int>(block = Long::toInt) })
} catch (_: Exception) {
	errors.run<VMErrors, Unit> {
		this.SystemCallGeneralException(message = "exit")
	}
}