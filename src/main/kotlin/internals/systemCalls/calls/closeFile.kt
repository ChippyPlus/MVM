package internals.systemCalls.calls

import internals.systemCalls.SystemCall
import org.example.data.registers.enumIdenifiers.SuperRegisterType
import org.example.errors
import org.example.fileDescriptors
import org.example.helpers.fullRegisterRead

fun SystemCall.closeFile(s2: SuperRegisterType) {
    val fd = fullRegisterRead(s2)
    if (fileDescriptors.fds.remove(fd) == null) {
        errors.InvalidFileDescriptorException(fd.toString())
    }
}