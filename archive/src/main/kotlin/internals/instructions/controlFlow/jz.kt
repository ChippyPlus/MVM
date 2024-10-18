package internals.instructions.controlFlow

import data.registers.RegisterType
import errors
import registers
import vm

/**
 * Performs a conditional jump to the target address if the value in the test register is zero.
 *
 * @param targetAddress The target address (line number) to jump to.
 * @param testRegister The register to check for a zero value.
 * @throws GeneralControlFlowException If an error occurs during the jump operation.
 */
fun ControlFlow.jz(targetAddress: Int, testRegister: RegisterType): Any = try {
	if (registers.read(testRegister) == 0L) {
		vm.pc = targetAddress
	} else {
		// I'm not sure why I need this else block
	}

} catch (_: Exception) {
	errors.run {
		this@run.GeneralControlFlowException(message = "Jz")
	}
}