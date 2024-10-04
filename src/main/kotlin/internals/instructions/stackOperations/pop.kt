package internals.instructions.stackOperations

import data.registers.enumIdenifiers.SuperRegisterType
import environment.VMErrors
import errors
import helpers.fullRegisterWrite

/**
 * Pops the top value from the stack and stores it in the specified register.
 *
 * @param destination The register to store the popped value.
 * @throws GeneralStackOperationsException If an error occurs during the pop operation (e.g. stack underflow).
 */
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