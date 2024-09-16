package internals.systemCalls.calls

import errors
import fileDescriptors
import helpers.fullRegisterRead
import helpers.writeRegisterString
import internals.systemCalls.SystemCall
import data.registers.enumIdenifiers.SuperRegisterType
import data.registers.enumIdenifiers.SuperRegisterType.R2
import helpers.VMFile
import helpers.fullRegisterWrite

@Suppress("RemoveExplicitTypeArguments")
fun SystemCall.readFile(fd: SuperRegisterType, buffer: SuperRegisterType): Unit = try {
    val f: VMFile =
        fileDescriptors.getFileDescriptor(fd = fullRegisterRead(register = fd)) ?: throw NullPointerException(
            "Expression 'fileDescriptors.getFileDescriptor(fd = fullRegisterRead(register = fd))' must not be null"
        )
    fullRegisterWrite(
        register = R2, value = writeRegisterString(register = buffer,
            string = with<VMFile, String>(receiver = f) { return@with this.file.run { return@run this.readText() } })
    )


} catch (_: Exception) {
    with(errors) {
        this@with.SystemCallGeneralException(message = buildString {
            append("readFile")
        })
    }
}