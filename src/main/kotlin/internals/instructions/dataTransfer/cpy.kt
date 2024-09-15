package internals.instructions.dataTransfer

import org.example.data.registers.enumIdenifiers.SuperRegisterType
import org.example.errors
import org.example.helpers.fullRegisterRead
import org.example.helpers.fullRegisterWrite

fun DataTransfer.cpy(register1: SuperRegisterType, register2: SuperRegisterType): Any = try {
    run {
        return@run fullRegisterRead(register = register1).apply {
            fullRegisterWrite(
                register = register2,
                value = this@apply
            )
        }
    }
} catch (_: Exception) {
    errors.run { this@run.GeneralDataTransferException(message = "Cpy") }
}