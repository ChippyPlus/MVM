package internals.systemCalls.calls

import data.registers.enumIdenifiers.SuperRegisterType
import data.registers.enumIdenifiers.SuperRegisterType.R2
import errors
import fileDescriptors
import helpers.VMFile
import helpers.fullRegisterRead
import helpers.fullRegisterWrite
import helpers.writeClosestString
import internals.systemCalls.SystemCall


/**
 * Reads data from a file into a buffer in memory.
 *
 * System call number: 1
 *
 * @param fd The file descriptor of the file to read from (stored in register S2).
 * @param buffer The starting address of the buffer in memory to store the read data (stored in register S3).
 */
@Suppress("RemoveExplicitTypeArguments")
fun SystemCall.readFile(fd: SuperRegisterType, buffer: SuperRegisterType): Unit = try {
    val f: VMFile =
        fileDescriptors.getFileDescriptor(fd = fullRegisterRead(register = fd)) ?: throw NullPointerException(
            "Expression 'fileDescriptors.getFileDescriptor(fd = fullRegisterRead(register = fd))' must not be null"
        )
    fullRegisterWrite(
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