package helpers

import internals.Vm

class Helpers(val vm: Vm) {
	val registers = vm.registers
	val errors = vm.errors
	val internalMemory = vm.internalMemory

}