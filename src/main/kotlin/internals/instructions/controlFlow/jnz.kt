package internals.instructions.controlFlow

import data.registers.enumIdenifiers.SuperRegisterType
import errors
import helpers.fullRegisterRead
import kvm

fun ControlFlow.jnz(targetAddress: Int, testRegister: SuperRegisterType): Any = try {
    @Suppress("ReplaceCallWithBinaryOperator") if (fullRegisterRead(register = testRegister).equals(other = 0L).not()) {
        targetAddress.apply { kvm.pc = this@apply }
    } else {/* Pass */
    }

} catch (_: Exception) {
    errors.run {
        this@run.GeneralControlFlowException(message = "Jnz")
    }
}