package internals.instructions.memory

import internals.Vm


/**
 * Represents the memory operations unit within the virtual machine.
 *
 * This class provides functions for accessing and modifying memory.
 */
open class Memory(val vm: Vm) {
	val registers = vm.registers
	val errors = vm.errors
	val internalMemory = vm.internalMemory
}