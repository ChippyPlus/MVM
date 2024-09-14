package internals.systemCalls.calls

import internals.systemCalls.SystemCall
import org.example.data.registers.enumIdenifiers.SuperRegisterType
import org.example.fileDescriptors
import org.example.helpers.VMFile
import org.example.helpers.fullRegisterWrite
import org.example.helpers.readRegisterString
import java.io.File

fun SystemCall.openFile(registerPath: SuperRegisterType) {
    val path = readRegisterString(registerPath)
    val f = File(path)
    val fd = fileDescriptors.addFileDescriptor(VMFile(f))
    fullRegisterWrite(SuperRegisterType.R2, fd)

}