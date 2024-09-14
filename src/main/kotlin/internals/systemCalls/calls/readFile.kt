package internals.systemCalls.calls

import internals.systemCalls.SystemCall
import org.example.data.registers.enumIdenifiers.SuperRegisterType
import org.example.fileDescriptors
import org.example.helpers.fullRegisterRead
import org.example.helpers.fullRegisterWrite
import org.example.helpers.writeRegisterString

fun SystemCall.readFile(fd: SuperRegisterType, buffer: SuperRegisterType) {
    val f = fileDescriptors.getFileDescriptor(fullRegisterRead(fd))!!
    fullRegisterWrite(SuperRegisterType.R2, writeRegisterString(buffer, f.file.readText()))


}