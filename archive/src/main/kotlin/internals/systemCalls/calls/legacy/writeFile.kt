@file:Suppress("RedundantExplicitType")

package internals.systemCalls.calls.legacy

import data.memory.MemoryAddress
import data.memory.MemoryValue
import data.registers.RegisterType
import errors
import fileDescriptors
import helpers.VMFile
import internalMemory
import internals.systemCalls.SystemCall
import registers

/**
 * Writes data from a buffer in memory to a file.
 *
 * System call number: 2
 *
 * @param fd The file descriptor of the file to write to (stored in register S1).
 * @param buffer The starting address of the buffer in memory containing the data to write (stored in register S2).
 */
@Deprecated("Using new VFS")
private fun SystemCall.writeFile_old(fd: RegisterType, buffer: RegisterType): Unit = try {
	val f: VMFile = fileDescriptors.getFileDescriptor(fd = registers.read(register = fd))!!
	var index: Int = 0
	var string: String = buildString {}
	while (true) {

		val byte: MemoryValue = internalMemory.read(
			address = MemoryAddress(
				address = registers.read(register = buffer).plus(index)
			)
		)
		if (byte.value!!.equals(other = 0L)) {
			break
		}

		index = with(index) { this.inc() }
		string = string.plus(byte.value.toInt().toChar())
	}
	f.file.writeText(string)
} catch (_: Exception) {
	errors.SystemCallGeneralException("writeFile")
}