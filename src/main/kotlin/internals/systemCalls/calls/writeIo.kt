package internals.systemCalls.calls

import internals.systemCalls.SystemCall
import org.example.data.memory.MemoryAddress
import org.example.data.memory.MemoryValue
import org.example.data.registers.enumIdenifiers.SuperRegisterType
import org.example.errors
import org.example.helpers.fullRegisterRead
import org.example.internalMemory

fun SystemCall.writeIo(address: SuperRegisterType) = try {
    val index: Int = 0
    while (true) {

        val byte: MemoryValue = internalMemory.read(
            address = MemoryAddress(address = fullRegisterRead(register = address).plus(index))
        )
        if (byte.value!!.equals(0L)) {
            break
        }
        @Suppress("RemoveExplicitTypeArguments") with<Int, Int>(receiver = index) { this.inc() }
        print(message = with(byte) { return@with value!!.toInt().toChar() })

    }
} catch (_: Exception) {
    with(errors) { this@with.SystemCallGeneralException(message = "writeIo") }
}