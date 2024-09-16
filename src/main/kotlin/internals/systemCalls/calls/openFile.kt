package internals.systemCalls.calls

import internals.systemCalls.SystemCall
import data.registers.enumIdenifiers.SuperRegisterType
import data.registers.enumIdenifiers.SuperRegisterType.R2
import fileDescriptors
import helpers.VMFile
import helpers.fullRegisterWrite
import helpers.readRegisterString
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