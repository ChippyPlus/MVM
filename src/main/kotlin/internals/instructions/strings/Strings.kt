package internals.instructions.strings

import internals.Vm

/**
 * Represents the string operations unit within the virtual machine.
 *
 * This class provides functions for manipulating strings, including string comparison, concatenation,
 * copying, and calculating string length.
 */
@Deprecated("Moved into stdlib functions")
open class Strings(vm: Vm) {
	val helpers = vm.helpers
	val registers = vm.registers
	val errors = vm.errors
	val internalMemory = vm.internalMemory
}