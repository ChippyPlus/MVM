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
fun StackOperations.peek(destination: RegisterType) = try {
	registers.write(
		register = destination, value = internalStack.peek()
	)

} catch (_: Exception) {
	errors.GeneralDataTransferException("Peek")
}