package internals.instructions.stackOperations

import data.registers.RegisterType
import errors
import registers


/**
 * Pushes the value from the specified register onto the stack.
 *
 * @param registerType The register containing the value to push.
 * @throws GeneralStackOperationsException If an error occurs during the push operation (e.g. stack overflow).
 */
fun StackOperations.push(registerType: RegisterType) = try {
	internalStack.push(element = registers.read(register = registerType))
} catch (_: Exception) {
	errors.GeneralDataTransferException("Push")

}