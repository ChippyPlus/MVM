package internals.systemCalls.calls

import internals.systemCalls.SystemCall
import org.example.data.registers.enumIdenifiers.SuperRegisterType
import org.example.helpers.fullRegisterWrite
import java.io.BufferedReader
import java.io.InputStreamReader

fun SystemCall.getUid() {
    val p: Process = Runtime.getRuntime().exec("id -u")
    val reader = BufferedReader(InputStreamReader(p.inputStream))
    val uid: Long = reader.readLine().toLong()
    fullRegisterWrite(SuperRegisterType.R2, uid)
}