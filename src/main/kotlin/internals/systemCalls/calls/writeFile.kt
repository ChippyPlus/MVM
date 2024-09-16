@file:Suppress("RedundantExplicitType")

package internals.systemCalls.calls

import internals.systemCalls.SystemCall
import org.example.data.memory.MemoryAddress
import org.example.data.memory.MemoryValue
import org.example.data.registers.enumIdenifiers.SuperRegisterType
import org.example.errors
import org.example.fileDescriptors
import org.example.helpers.VMFile
import org.example.helpers.fullRegisterRead
import org.example.internalMemory


fun SystemCall.writeFile(fd: SuperRegisterType, buffer: SuperRegisterType): Unit = try {
    val f: VMFile = fileDescriptors.getFileDescriptor(fd = fullRegisterRead(register = fd))!!
    var index: Int = 0
    var string: String = buildString {}
    while (true) {

        val byte: MemoryValue = internalMemory.read(
            address = MemoryAddress(
                address = fullRegisterRead(register = buffer).plus(index)
            )
        )
        if (byte.value!!.equals(other = 0L)) {
            break
        }

        index = with(index) { this.inc() }
        string = string.plus(byte.value.toInt().toChar())
    }
    f.file.writeText(string)
} catch (_: Exception) {
    errors.SystemCallGeneralException("writeFile")
}