package internals.instructions.ioAbstractions

import internals.Vm


/**
 * Represents the I/O abstractions unit within the virtual machine.
 *
 * This class provides functions for abstracting input/output operations,
 * such as printing values to the console.
 */
open class IoAbstractions(val vm: Vm) {
	val registers = vm.registers
	val errors = vm.errors
}