package environment.libEx

import environment.ExecuteLib
import kilb.Klib

fun ExecuteLib.executeKt(name: String) {
	val oldPc = vm.pc
	val snapshot = vm.snapShotManager.fullSnapshot()
	if (!Klib(vm).match(name)) {
		vm.errors.MissingLibraryException(name) // Kt should be the last resort
	}
	currentFunction = name
	vm.snapShotManager.populateSnapShot(snapshot)
	vm.pc = oldPc
}