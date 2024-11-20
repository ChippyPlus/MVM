package environment.reflection

import internals.Vm

data class KProcess(val vm: Vm, var thread: Thread? = null) {
	val ipcPermissions = mutableListOf<Int>()
	var parent: Int? = null

	init {
		reflection.vmTracker.add(this)
	}

	val id = reflection.vmTracker.size
}
