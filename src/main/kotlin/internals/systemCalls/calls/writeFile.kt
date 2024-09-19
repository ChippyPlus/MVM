@file:Suppress("RedundantExplicitType")

package internals.systemCalls.calls

import data.memory.MemoryAddress
import data.memory.MemoryValue
import data.registers.enumIdenifiers.SuperRegisterType
import errors
import fileDescriptors
import helpers.VMFile
import helpers.registerRead
import internalMemory
import internals.systemCalls.SystemCall

/**
 * Writes data from a buffer in memory to a file.
 *
 * System call number: 2
 *
 * @param fd The file descriptor of the file to write to (stored in register S2).
 * @param buffer The starting address of the buffer in memory containing the data to write (stored in register S3).
 */
fun SystemCall.writeFile(fd: SuperRegisterType, buffer: SuperRegisterType): Unit = try {
    val f: VMFile = fileDescriptors.getFileDescriptor(fd = registerRead(register = fd) as Long)!!
    var index: Int = 0
    var string: String = buildString {}
    while (true) {

        val byte: MemoryValue = internalMemory.read(
            address = MemoryAddress(
                address = (registerRead(register = buffer) as Long).plus(index)
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