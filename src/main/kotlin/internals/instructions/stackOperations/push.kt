package internals.instructions.stackOperations

import data.registers.enumIdenifiers.SuperRegisterType
import environment.VMErrors
import errors
import helpers.registerRead


/**
 * Pushes the value from the specified register onto the stack.
 *
 * @param registerType The register containing the value to push.
 * @throws GeneralStackOperationsException If an error occurs during the push operation (e.g., stack overflow).
 */
fun StackOperations.push(registerType: SuperRegisterType) = try {
    @Suppress("UNNECESSARY_NOT_NULL_ASSERTION") this@push.internalStack!!.push(element = registerRead(register = registerType))
} catch (_: Exception) {
    @Suppress("RemoveExplicitTypeArguments") with<VMErrors, Unit>(receiver = errors) {
        GeneralStackOperationsException(
            message = "push"
        )
    }
}