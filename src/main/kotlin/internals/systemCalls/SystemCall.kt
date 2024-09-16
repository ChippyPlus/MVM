package internals.systemCalls

import internals.systemCalls.calls.*
import data.registers.enumIdenifiers.SuperRegisterType
import helpers.fullRegisterRead


/**
 * **System Call Instruction:**
 *
 * * `SYSCALL` - Execute a system call (requires system call number and arguments).
 */
class SystemCall {
    fun execute(callId: SuperRegisterType, s2: SuperRegisterType, s3: SuperRegisterType, s4: SuperRegisterType) {
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


            else -> throw RuntimeException("Invalid system call ID: $callId")
        }

    }
}