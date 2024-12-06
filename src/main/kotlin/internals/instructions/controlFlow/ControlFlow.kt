package internals.instructions.controlFlow

import internals.Vm


/**
 * Represents the control flow operations unit within the virtual machine.
 *
 * This class provides functions for managing the execution flow of instructions,
 * including jumps and conditional branches.
 */
open class ControlFlow(val vm: Vm) {
	val errors = vm.errors
	val registers = vm.registers
}