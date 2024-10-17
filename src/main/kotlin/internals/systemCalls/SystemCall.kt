package internals.systemCalls

import data.registers.enumIdenifiers.SuperRegisterType
import errors
import helpers.registerRead
import internals.systemCalls.calls.*

/**
 * Handles the execution of system calls within the virtual machine.
 */
class SystemCall {

	/**
	 * Executes the system call specified by the call ID.
	 *
	 * @param callId The system call number, identifying the system call to execute (stored in register S0).
	 * @param s2 The register containing the first argument to the system call (stored in register S1).
	 * @param s3 The register containing the second argument to the system call (stored in register S2).
	 * @param s4 The register containing the third argument to the system call (stored in register S3).
	 * @throws InvalidSystemCallException If the provided system call number is invalid.
	 */
	fun execute(
		callId: SuperRegisterType,
		s2: SuperRegisterType,
		s3: SuperRegisterType,
		s4: SuperRegisterType,
	) {
		when (registerRead(callId).toInt()) {
			1 -> newFile(s2)
			2 -> readFile(s2)
			3 -> writeFile(s2)
			4 -> closeFile(s2)
			5 -> exit(s2)
			14 -> time()
			16 -> getPid()
			17 -> getUid()
			24 -> writeIo(s2)
			25 -> readIo()
			26 -> createArray(s2)
			27 -> arraySet(s2, s3, s4)
			28 -> arrayGet(s2, s3)
			else -> errors.InvalidSystemCallException(registerRead(callId).toString())
		}
	}
}