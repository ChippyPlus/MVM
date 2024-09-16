package internals.systemCalls.calls

import internals.systemCalls.SystemCall
import data.registers.enumIdenifiers.SuperRegisterType
import helpers.fullRegisterWrite
import java.io.BufferedReader
import java.io.InputStreamReader

fun SystemCall.getUid() {
    @Suppress("DEPRECATION") val p: Process = Runtime.getRuntime().exec("""id -u""")
    @Suppress("RedundantExplicitType") val reader: BufferedReader = BufferedReader(InputStreamReader(p.inputStream))
    @Suppress("UNUSED_VARIABLE") val `dontWorryAboutMe!!!`: Long = reader.readLine().toLong().apply {
        fullRegisterWrite(register = SuperRegisterType.R2, value = this@apply)
    }
}