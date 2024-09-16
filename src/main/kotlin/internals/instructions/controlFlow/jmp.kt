package internals.instructions.controlFlow

import errors
import kvm


fun ControlFlow.jmp(targetAddress: Int): Any = try {
    targetAddress.apply { kvm.pc = this@apply }
} catch (_: Exception) {
    errors.run { this@run.GeneralControlFlowException(message = "Jmp") }
}