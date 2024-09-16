package internals.systemCalls.calls

import internals.systemCalls.SystemCall
import data.memory.MemoryAddress
import data.memory.MemoryValue
import data.registers.enumIdenifiers.SuperRegisterType
import errors
import helpers.fullRegisterRead
import internalMemory

fun SystemCall.writeIo(address: SuperRegisterType) = try {
    var index: Int = 0
    while (true) {

        val byte: MemoryValue = internalMemory.read(
            address = MemoryAddress(address = fullRegisterRead(register = address).plus(index))
        )
        if (byte.value!!.equals(0L)) {
            break
        }

        @Suppress("RemoveExplicitTypeArguments")
        index = with<Int, Int>(receiver = index) { this.inc() }
        print(message = with(byte) { return@with value!!.toInt().toChar() })

    }
} catch (_: Exception) {
    with(errors) { this@with.SystemCallGeneralException(message = "writeIo") }
}