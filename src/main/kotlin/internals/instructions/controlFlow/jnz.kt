package internals.instructions.controlFlow

import data.registers.RegisterType
import errors
import registers
import vm


/**
 * Performs a conditional jump to the target address if the value in the test register is not zero.
 *
 * @param targetAddress The target address (line number) to jump to.
 * @param testRegister The register to check for a non-zero value.
 * @throws GeneralControlFlowException If an error occurs during the jump operation.
 */
fun ControlFlow.jnz(targetAddress: Int, testRegister: RegisterType): Any = try {

	if (registers.read(testRegister) != 0L) {
		vm.pc = targetAddress
	} else {
		// I'm not sure why I need this else block
	}

} catch (_: Exception) {
	errors.run {
		this.GeneralControlFlowException(message = "Jnz")
	}
}