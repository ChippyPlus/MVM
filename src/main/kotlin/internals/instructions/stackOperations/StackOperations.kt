package internals.instructions.stackOperations

import data.stack.FixedStack
import internals.Vm


/**
 * Represents the stack operations unit within the virtual machine.
 *
 * This class provides functions for managing the stack, including pushing, popping, and peeking at elements.
 *
 * @property limit The maximum size of the stack.
 */
class StackOperations(val vm: Vm, limit: Int) {
	val registers = vm.registers
	val errors = vm.errors
	val internalStack = FixedStack(limit, vm)
}