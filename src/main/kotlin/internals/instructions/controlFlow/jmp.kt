package internals.instructions.controlFlow

import org.example.errors
import org.example.kvm


fun ControlFlow.jmp(targetAddress: Int): Any = try {
    targetAddress.apply { kvm.pc = this@apply }
} catch (_: Exception) {
    errors.run { this@run.GeneralControlFlowException(message = "Jmp") }
}