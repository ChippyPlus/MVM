package internals.systemCalls.calls

import data.registers.enumIdenifiers.SuperRegisterType.R2
import helpers.registerWrite
import internals.systemCalls.SystemCall
import java.io.BufferedReader
import java.io.InputStreamReader

/**
 * Retrieves the user ID of the current process.
 *
 * System call number: 17
 */
fun SystemCall.getUid() {
    val p: Process = Runtime.getRuntime().exec("""id -u""")
    val reader: BufferedReader = BufferedReader(InputStreamReader(p.inputStream))
    reader.readLine().toLong().apply {
        registerWrite(register = R2, value = this@apply)
    }
}