package org.example.kvmInternals.systemCalls

import org.example.data.registers.enumIdenifiers.SuperRegisterType
import org.example.helpers.fullRegisterRead
import org.example.kvmInternals.systemCalls.calls.*


/**
 * **System Call Instruction:**
 *
 * * `SYSCALL` - Execute a system call (requires system call number and arguments).
 */
class SystemCall {
    fun execute(callId: SuperRegisterType, s2: SuperRegisterType, s3: SuperRegisterType, s4: SuperRegisterType) {
        when (fullRegisterRead(callId)) {
            1 -> readFile(s2, s3)
            2 -> writeFile(s2, s3)
            3 -> openFile(s2)
            4 -> closeFile(s2)
            6 -> exit(s2)
            15 -> getTimeOfday()
            17 -> getPid()
            18 -> getUid()
            25 -> writeIo(s2)


            else -> throw RuntimeException("Invalid system call ID: $callId")
        }

    }
}