package internals.instructions.dataTransfer

import org.example.data.registers.enumIdenifiers.SuperRegisterType
import org.example.errors
import org.example.helpers.fullRegisterRead
import org.example.helpers.fullRegisterWrite


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