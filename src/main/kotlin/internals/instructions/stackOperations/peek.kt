package internals.instructions.stackOperations

import data.registers.IntelRegisters
import data.registers.RegisterType
import data.registers.intelNames
import errors
import helpers.toDouble
import registers

/**
 * Pushes the value from the specified register onto the stack.
 *
 * @param registerType The register containing the value to push.
 * @throws GeneralStackOperationsException If an error occurs during the push operation (e.g. stack overflow).
 */
fun StackOperations.peek(destination: RegisterType) = try {
	registers.write(
		intelNames[IntelRegisters.ENSF], true.toDouble()
	) // Its above the next expr because internal stack may throw its own errors

	registers.write(
		register = destination, value = internalStack.peek()
	)

} catch (_: Exception) {
	errors.GeneralStackOperationsException("Peek")
}