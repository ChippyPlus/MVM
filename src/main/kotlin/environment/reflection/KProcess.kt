package environment.reflection

import internals.Vm

data class KProcess(val vm: Vm, var thread: Thread? = null) {
	val id = reflection.vmTracker.size + 1
}
