package environment.reflection

import internals.Vm


val reflection = Reflection()

class Reflection {
	val currentFileData = CurrentFileData()
	val vmTracker = mutableListOf<KProcess>()

	fun groupTrackedVmById(): Map<Int, KProcess> {
		val x = vmTracker.groupBy(KProcess::id)
		val y = mutableMapOf<Int, KProcess>()
		for (i in x) {
			y[i.key] = i.value[0]
		}
		return y
	}

	fun groupTrackedVmByVm(): MutableMap<Vm, KProcess> {
		val x = vmTracker.groupBy(KProcess::vm)
		val y = mutableMapOf<Vm, KProcess>()
		for (i in x) {
			y[i.key] = i.value[0]
		}
		return y
	}


}