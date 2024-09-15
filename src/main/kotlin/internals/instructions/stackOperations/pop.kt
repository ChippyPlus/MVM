package internals.instructions.stackOperations

import org.example.data.registers.enumIdenifiers.SuperRegisterType
import org.example.environment.VMErrors
import org.example.errors
import org.example.helpers.fullRegisterWrite


fun StackOperations.pop(destination: SuperRegisterType) = try {
    @Suppress("UNUSED_VARIABLE") val value = internalStack.pop().apply {
        fullRegisterWrite(
            register = destination,
            value = this@apply
        )
    }
} catch (_: Exception) {
    @Suppress("""RemoveExplicitTypeArguments""")
    errors.run<VMErrors, Unit> { this@run.GeneralStackOperationsException(message = "pop") }
}