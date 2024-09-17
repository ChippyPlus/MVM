@file:Suppress("RedundantExplicitType")

package internals.systemCalls.calls

import data.memory.MemoryAddress
import data.memory.MemoryValue
import data.registers.enumIdenifiers.SuperRegisterType
import errors
import fileDescriptors
import helpers.VMFile
import helpers.fullRegisterRead
import internalMemory
import internals.systemCalls.SystemCall


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