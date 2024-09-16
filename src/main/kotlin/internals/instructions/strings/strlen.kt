package internals.instructions.strings

import data.memory.MemoryAddress
import data.registers.enumIdenifiers.SuperRegisterType
import data.registers.enumIdenifiers.SuperRegisterType.R4
import environment.VMErrors
import errors
import helpers.fullRegisterRead
import helpers.fullRegisterWrite
import internalMemory

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