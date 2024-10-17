package internals.systemCalls.calls.legacy

import data.registers.enumIdenifiers.SuperRegisterType
import data.registers.enumIdenifiers.SuperRegisterType.R2
import errors
import fileDescriptors
import helpers.VMFile
import helpers.registerRead
import helpers.registerWrite
import helpers.writeClosestString
import internals.systemCalls.SystemCall


/**
 * Reads data from a file into a buffer in memory.
 *
 * System call number: 1
 *
 * @param fd The file descriptor of the file to read from (stored in register S1).
 * @param buffer The starting address of the buffer in memory to store the read data (stored in register S2).
 */
@Deprecated("Using new VFS")
private fun SystemCall.readFile_old(fd: SuperRegisterType): Unit = try {
	val f: VMFile =
		fileDescriptors.getFileDescriptor(fd = registerRead(register = fd)) ?: throw NullPointerException(
			"Expression 'fileDescriptors.getFileDescriptor(fd = fullRegisterRead(register = fd))' must not be null"
		)
	registerWrite(
		register = R2,
		value = writeClosestString(string = f.file.readText())
	)


} catch (_: Exception) {
	with(errors) {
		this@with.SystemCallGeneralException(message = buildString {
			append("readFile")
		})
	}
}