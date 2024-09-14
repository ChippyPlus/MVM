package internals.systemCalls.calls

import internals.systemCalls.SystemCall
import org.example.data.registers.enumIdenifiers.SuperRegisterType
import org.example.helpers.fullRegisterWrite

fun SystemCall.getPid() {
    fullRegisterWrite(SuperRegisterType.R2, ProcessHandle.current().pid())

}