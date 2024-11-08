package internals.instructions.controlFlow

import errors
import vm

/**
 * Performs an unconditional jump to the specified target address.
 *
 * @param targetAddress The target address (line number) to jump to.
 * @throws GeneralControlFlowException If an error occurs during the jump operation.
 */
fun ControlFlow.jmp(targetAddress: Long): Any = try {
	vm.pc = targetAddress.toInt()
} catch (_: Exception) {
	errors.GeneralControlFlowException(message = "Jmp")
}