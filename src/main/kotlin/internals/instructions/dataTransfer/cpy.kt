package internals.instructions.dataTransfer

import data.registers.enumIdenifiers.SuperRegisterType
import errors
import helpers.fullRegisterRead
import helpers.fullRegisterWrite

/**
 * Copies the value from one register to another.
 *
 * @param register1 The source register.
 * @param register2 The destination register.
 * @throws GeneralDataTransferException If an error occurs during the copy operation.
 */
fun DataTransfer.cpy(register1: SuperRegisterType, register2: SuperRegisterType): Any = try {
    run {
        return@run fullRegisterRead(register = register1).apply {
            fullRegisterWrite(
                register = register2, value = this@apply
            )
        }
    }
} catch (_: Exception) {
    errors.run { this@run.GeneralDataTransferException(message = "Cpy") }
}