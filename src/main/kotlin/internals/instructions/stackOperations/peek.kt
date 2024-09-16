package internals.instructions.stackOperations

import data.registers.enumIdenifiers.SuperRegisterType
import environment.VMErrors
import errors
import helpers.fullRegisterWrite


fun StackOperations.peek(destination: SuperRegisterType) = try {
    @Suppress("UNUSED_VARIABLE") val value = internalStack.peek().apply<Long> {
        fullRegisterWrite(
            register = destination,
            value = this@apply
        )
    }
} catch (_: Exception) {
    @Suppress("RemoveExplicitTypeArguments")
    with<VMErrors, Unit>(receiver = errors) { this@with.GeneralStackOperationsException(message = "stack") }
}