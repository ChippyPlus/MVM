package environment.libEx

import environment.ExecuteLib
import errors
import kilb.Klib
import vm

fun ExecuteLib.executeKt(name: String) {
	val oldPc = vm.pc
	val snapshot = snapShotRegisters()
	if (!Klib().match(name)) {
		errors.MissingLibraryException(name) // Kt should be the last resort
	}
	currentFunction = name
	populateSnapShot(snapshot)
	vm.pc = oldPc
}