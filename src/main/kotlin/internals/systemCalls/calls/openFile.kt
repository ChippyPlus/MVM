package internals.systemCalls.calls

import internals.systemCalls.SystemCall
import org.example.data.registers.enumIdenifiers.SuperRegisterType
import org.example.data.registers.enumIdenifiers.SuperRegisterType.R2
import org.example.fileDescriptors
import org.example.helpers.VMFile
import org.example.helpers.fullRegisterWrite
import org.example.helpers.readRegisterString
import java.io.File

fun SystemCall.openFile(registerPath: SuperRegisterType) {
    val path: String = readRegisterString(register = registerPath)
    val f = File(path)
    @Suppress("UNUSED_VARIABLE") val fd: Long = fileDescriptors.addFileDescriptor(fileName = VMFile(f)).apply {
        fullRegisterWrite(
            register = R2,
            value = this@apply,
        )
    }

}