package internals.systemCalls.calls

import data.registers.RegisterType.R2
import internals.systemCalls.SystemCall

/**
 * Retrieves the process ID of the current process.
 *
 * System call number: 16
 */
fun SystemCall.getPid() = call("getPid") {
	registers.write(
		register = R2, value = ProcessHandle.current().pid()
	)
}