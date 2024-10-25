package internals.systemCalls.calls.legacy

import data.registers.RegisterType
import errors
import fileDescriptors
import internals.systemCalls.SystemCall
import registers

/**
 * Closes the file associated with the file descriptor.
 *
 * System call number: 4
 *
 * @param s2 The register containing the file descriptor to close (stored in register S1).
 */
@Deprecated("Using new VFS")
private fun SystemCall.closeFile_old(s2: RegisterType): Unit = try {
	val fd: Long = registers.read(register = s2).toLong()

	if (fileDescriptors.fds.remove(fd)?.equals(null)
			?: throw NullPointerException("Expression 'fileDescriptors.fds.remove(fd)?.equals(null)' must not be null")
	) {
		errors.run {
			this@run.InvalidFileDescriptorException(message = with(fd) { return@with this.toString() })
		}
	} else {/* pass */

	}
} catch (e: Exception) {
	with(receiver = errors, block = {
		this@with.SystemCallGeneralException(message = "closeFile")
	})
}