package internals.instructions.stackOperations

import data.registers.enumIdenifiers.SuperRegisterType
import environment.VMErrors
import errors
import helpers.registerWrite

/**
 * Pushes the value from the specified register onto the stack.
 *
 * @param registerType The register containing the value to push.
 * @throws GeneralStackOperationsException If an error occurs during the push operation (e.g. stack overflow).
 */
fun StackOperations.peek(destination: SuperRegisterType) = try {
    @Suppress("UNUSED_VARIABLE") val value = internalStack.peek().apply {
        registerWrite(
            register = destination, value = this@apply
        )
    }
} catch (_: Exception) {
    @Suppress("RemoveExplicitTypeArguments") with<VMErrors, Unit>(receiver = errors) {
        this@with.GeneralStackOperationsException(
            message = "stack"
        )
    }
}