package internals.systemCalls.calls

import internals.systemCalls.SystemCall
import org.example.data.registers.enumIdenifiers.SuperRegisterType
import org.example.helpers.fullRegisterWrite

fun SystemCall.getTimeOfday() {
    fullRegisterWrite(SuperRegisterType.R2, System.currentTimeMillis())
}