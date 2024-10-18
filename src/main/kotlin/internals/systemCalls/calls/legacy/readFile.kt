package internals.systemCalls.calls.legacy

import data.registers.RegisterType
import data.registers.RegisterType.R2
import errors
import fileDescriptors
import helpers.VMFile

import helpers.writeClosestString
import internals.systemCalls.SystemCall
import registers


/**
 * Reads data from a file into a buffer in memory.
 *
 * System call number: 1
 *
 * @param fd The file descriptor of the file to read from (stored in register S1).
 * @param buffer The starting address of the buffer in memory to store the read data (stored in register S2).
 */
@Deprecated("Using new VFS")
private fun SystemCall.readFile_old(fd: RegisterType): Unit = try {
	val f: VMFile =
		fileDescriptors.getFileDescriptor(fd = registers.read(register = fd)) ?: throw NullPointerException(
			"Expression 'fileDescriptors.getFileDescriptor(fd = fullRegisterRead(register = fd))' must not be null"
		)
	registers.write(
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