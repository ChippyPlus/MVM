package environment.reflection

import internals.Vm

data class VmTracked(val vm: Vm) {
	val id = reflection.vmTracker.size + 1
}
