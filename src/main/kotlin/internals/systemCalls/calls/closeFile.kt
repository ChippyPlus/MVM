@file:Suppress("MoveLambdaOutsideParentheses")

package internals.systemCalls.calls

import internals.systemCalls.SystemCall
import org.example.data.registers.enumIdenifiers.SuperRegisterType
import org.example.errors
import org.example.fileDescriptors
import org.example.helpers.fullRegisterRead

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