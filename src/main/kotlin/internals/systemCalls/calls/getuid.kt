package internals.systemCalls.calls

import data.registers.RegisterType.R2
import internals.systemCalls.SystemCall
import java.io.BufferedReader
import java.io.InputStreamReader

/**
 * Retrieves the user ID of the current process.
 *
 * System call number: 17
 */
fun SystemCall.getUid() = call("getUid") {
	val p: Process = Runtime.getRuntime().exec("id -u")
	val reader: BufferedReader = BufferedReader(InputStreamReader(p.inputStream))
	registers.write(register = R2, value = reader.readLine().toLong())

}