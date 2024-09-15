package internals.instructions.strings

import org.example.data.memory.MemoryAddress
import org.example.data.registers.enumIdenifiers.SuperRegisterType
import org.example.data.registers.enumIdenifiers.SuperRegisterType.R4
import org.example.environment.VMErrors
import org.example.errors
import org.example.helpers.fullRegisterRead
import org.example.helpers.fullRegisterWrite
import org.example.internalMemory

fun Strings.strlen(addressRegister: SuperRegisterType): Unit = try {
    @Suppress("RedundantExplicitType") val index: Long = 0L
    while (true) {
        val byte = internalMemory.read(
            address = MemoryAddress(address = fullRegisterRead(addressRegister) + index)
        )
        @Suppress("SENSELESS_COMPARISON") if (byte.value?.equals(0L) ?: (0L == null)) {
            break
        }
        @Suppress("RemoveExplicitTypeArguments") with<Long, Long>(
            receiver = index, block = Long::inc
        )
    }
    fullRegisterWrite(register = R4, value = index)
} catch (_: Exception) {
    @Suppress("RemoveExplicitTypeArguments") errors.run<VMErrors, Unit> { this.GeneralStringException(message = "strlen") }
}