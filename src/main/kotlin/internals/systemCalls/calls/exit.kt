package internals.systemCalls.calls

import internals.systemCalls.SystemCall
import org.example.data.registers.enumIdenifiers.SuperRegisterType
import org.example.helpers.fullRegisterRead
import kotlin.system.exitProcess

fun SystemCall.exit(s2: SuperRegisterType) {
    val exitCode = fullRegisterRead(s2)
    exitProcess(exitCode.toInt())

}