package internals.systemCalls.calls

import internals.systemCalls.SystemCall
import org.example.data.registers.enumIdenifiers.SuperRegisterType
import org.example.data.registers.enumIdenifiers.SuperRegisterType.R2
import org.example.errors
import org.example.fileDescriptors
import org.example.helpers.VMFile
import org.example.helpers.fullRegisterRead
import org.example.helpers.fullRegisterWrite
import org.example.helpers.writeRegisterString

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