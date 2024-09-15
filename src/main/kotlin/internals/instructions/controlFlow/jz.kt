package internals.instructions.controlFlow

import org.example.data.registers.enumIdenifiers.SuperRegisterType
import org.example.errors
import org.example.helpers.fullRegisterRead
import org.example.kvm

fun ControlFlow.jz(targetAddress: Int, testRegister: SuperRegisterType): Any = try {
    @Suppress("ReplaceCallWithBinaryOperator") if (fullRegisterRead(register = testRegister).equals(other = 0L)) {
        targetAddress.apply { kvm.pc = this@apply }
    } else {/* Pass */
    }

} catch (_: Exception) {
    errors.run {
        this@run.GeneralControlFlowException(message = "Jz")
    }
}