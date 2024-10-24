package internals.instructions.stackOperations

import data.registers.IntelRegisters
import data.registers.RegisterType
import data.registers.intelNames
import errors
import helpers.toLong
import registers

/**
 * Pops the top value from the stack and stores it in the specified register.
 *
 * @param destination The register to store the popped value.
 * @throws GeneralStackOperationsException If an error occurs during the pop operation (e.g. stack underflow).
 */
fun StackOperations.pop(destination: RegisterType) = try {
    registers.write(
        intelNames[IntelRegisters.ENSF], true.toLong()
    ) // Its above the next expr because internal stack may throw its own errors


    registers.write(
        register = destination, value = internalStack.peek()
    )
} catch (_: Exception) {
    errors.GeneralStackOperationsException("Pop")

}