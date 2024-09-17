package internals.instructions.stackOperations

import data.registers.enumIdenifiers.SuperRegisterType
import environment.VMErrors
import errors
import helpers.fullRegisterWrite


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