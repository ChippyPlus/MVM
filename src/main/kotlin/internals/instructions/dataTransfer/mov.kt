package internals.instructions.dataTransfer
import data.registers.enumIdenifiers.SuperRegisterType
import errors
import helpers.registerRead
import helpers.registerWrite

/**
 * Moves the value from the source register to the destination register.
 *
 * @param source The source register.
 * @param destination The destination register.
 * @throws GeneralDataTransferException If an error occurs during the move operation.
 */
fun DataTransfer.mov(source: SuperRegisterType, destination: SuperRegisterType): Unit = try {
    @Suppress("UNUSED_VARIABLE") val value: Long = registerRead(register = source).apply {
        registerWrite(
            register = destination,
            value = this@apply,
        )
    }
} catch (_: Exception) {
    errors.run {
        this@run.GeneralDataTransferException(message = "mov")
    }
}