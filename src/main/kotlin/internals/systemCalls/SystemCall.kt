package internals.systemCalls

import data.registers.enumIdenifiers.SuperRegisterType
import errors
import helpers.fullRegisterRead
import internals.systemCalls.calls.*

/**
 * Handles the execution of system calls within the virtual machine.
 */
class SystemCall {

    /**
     * Executes the system call specified by the call ID.
     *
     * @param callId The system call number, identifying the system call to execute (stored in register S1).
     * @param s2 The register containing the first argument to the system call (stored in register S2).
     * @param s3 The register containing the second argument to the system call (stored in register S3).
     * @param s4 The register containing the third argument to the system call (stored in register S4).
     * @throws InvalidSystemCallException If the provided system call number is invalid.
     */
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