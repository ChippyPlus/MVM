package internals.instructions.controlFlow

import data.registers.enumIdenifiers.SuperRegisterType
import errors
import helpers.registerRead
import vm

/**
 * Performs a conditional jump to the target address if the value in the test register is zero.
 *
 * @param targetAddress The target address (line number) to jump to.
 * @param testRegister The register to check for a zero value.
 * @throws GeneralControlFlowException If an error occurs during the jump operation.
 */
fun ControlFlow.jz(targetAddress: Int, testRegister: SuperRegisterType): Any = try {
    @Suppress("ReplaceCallWithBinaryOperator") if (registerRead(register = testRegister).equals(other = 0L)) {
        targetAddress.apply { vm.pc = this@apply }
    } else {/* Pass */
    }

} catch (_: Exception) {
    errors.run {
        this@run.GeneralControlFlowException(message = "Jz")
    }
}