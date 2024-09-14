package internals.instructions.controlFlow

import org.example.errors
import org.example.kvm

fun ControlFlow.jmp(targetAddress: Int) = try {
    kvm.pc = targetAddress
} catch (_: Exception) {
    errors.GeneralControlFlowException("Jmp")
}