package internals.instructions.stackOperations

import data.registers.RegisterType
import errors
import registers

/**
 * Pops the top value from the stack and stores it in the specified register.
 *
 * @param destination The register to store the popped value.
 * @throws GeneralStackOperationsException If an error occurs during the pop operation (e.g. stack underflow).
 */
fun StackOperations.pop(destination: RegisterType) = try {
    registers.write(
        register = destination, value = internalStack.peek()
    )
} catch (_: Exception) {
    errors.GeneralDataTransferException("Pop")

}