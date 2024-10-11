@file:Suppress("MoveLambdaOutsideParentheses")

package internals.systemCalls.calls

import data.registers.enumIdenifiers.SuperRegisterType
import errors
import fileDescriptors
import helpers.fullRegisterRead
import internals.systemCalls.SystemCall

/**
 * Closes the file associated with the file descriptor.
 *
 * System call number: 4
 *
 * @param s2 The register containing the file descriptor to close (stored in register S1).
 */
fun SystemCall.closeFile(s2: SuperRegisterType): Unit = try {
    val fd: Long = fullRegisterRead(register = s2)

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