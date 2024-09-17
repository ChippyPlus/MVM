package internals.systemCalls

import data.registers.enumIdenifiers.SuperRegisterType
import errors
import helpers.fullRegisterRead
import internals.systemCalls.calls.*


class SystemCall {
    fun execute(
        callId: SuperRegisterType,
        s2: SuperRegisterType,
        s3: SuperRegisterType,
        @Suppress("UNUSED_PARAMETER") s4: SuperRegisterType,
    ) {
        when (fullRegisterRead(callId).toInt()) {
            1 -> readFile(s2, s3)
            2 -> writeFile(s2, s3)
            3 -> openFile(s2)
            4 -> closeFile(s2)
            5 -> exit(s2)
            14 -> getTimeOfday()
            16 -> getPid()
            17 -> getUid()
            24 -> writeIo(s2)


            else -> errors.InvalidSystemCallException(fullRegisterRead(callId).toString())
        }

    }
}