package environment.libEx

import environment.ExecuteLib
import environment.snapShotManager
import kilb.Klib

fun ExecuteLib.executeKt(name: String) {
	val oldPc = vm.pc
	val snapshot = snapShotManager.fullSnapshot()
	if (!Klib().match(name)) {
		errors.MissingLibraryException(name) // Kt should be the last resort
	}
	currentFunction = name
	snapShotManager.populateSnapShot(snapshot)
	vm.pc = oldPc
}