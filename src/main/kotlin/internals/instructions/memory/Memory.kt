package internals.instructions.memory

import environment.reflection.reflection
import internals.Vm


/**
 * Represents the memory operations unit within the virtual machine.
 *
 * This class provides functions for accessing and modifying memory.
 */
open class Memory(val vm: Vm) {
	val heap = reflection.groupTrackedVmByVm()[vm]!!.addressSpace.heap
	val registers = vm.registers
	val errors = vm.errors
}