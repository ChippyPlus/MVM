package internals.instructions.controlFlow

import errors
import kvm

/**
 * Performs an unconditional jump to the specified target address.
 *
 * @param targetAddress The target address (line number) to jump to.
 * @throws GeneralControlFlowException If an error occurs during the jump operation.
 */
fun ControlFlow.jmp(targetAddress: Int): Any = try {
    targetAddress.apply { kvm.pc = this@apply }
} catch (_: Exception) {
    errors.run { this@run.GeneralControlFlowException(message = "Jmp") }
}