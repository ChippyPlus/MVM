package internals.instructions.dataTransfer

import data.registers.enumIdenifiers.SuperRegisterType
import errors
import helpers.fullRegisterRead
import helpers.fullRegisterWrite


fun DataTransfer.mov(Source: SuperRegisterType, Destination: SuperRegisterType): Unit = try {
    @Suppress("UNUSED_VARIABLE") val value: Long = fullRegisterRead(register = Source).apply {
        fullRegisterWrite(
            register = Destination,
            value = this@apply,
        )
    }
} catch (_: Exception) {
    errors.run {
        this@run.GeneralDataTransferException(message = "mov")
    }
}