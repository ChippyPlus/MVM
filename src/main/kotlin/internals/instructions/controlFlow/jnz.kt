package internals.instructions.controlFlow

import org.example.data.registers.enumIdenifiers.SuperRegisterType
import org.example.errors
import org.example.helpers.fullRegisterRead
import org.example.kvm

fun ControlFlow.jnz(targetAddress: Int, testRegister: SuperRegisterType) = try {
    if (fullRegisterRead(testRegister) != 0L) {
        kvm.pc = targetAddress
    } else {
        // Pass
    }

} catch (_: Exception) {
    errors.GeneralControlFlowException("Jnz")
}