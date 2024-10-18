package internals.systemCalls.calls

import data.registers.RegisterType.R2
import errors
import internals.systemCalls.SystemCall
import registers

/**
 * Retrieves the process ID of the current process.
 *
 * System call number: 16
 */
fun SystemCall.getPid(): Unit = try {
	registers.write(
		register = R2, value = ProcessHandle.current().pid()
	)
} catch (_: Exception) {
	errors.SystemCallGeneralException(message = "exit")

}