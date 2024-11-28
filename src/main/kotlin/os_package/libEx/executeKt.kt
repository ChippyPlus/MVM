package os_package.libEx

import kilb.Klib
import os_package.ExecuteLib

fun ExecuteLib.executeKt(name: String) {
	val oldPc = vm.pc
	val snapshot = vm.snapShotManager.snapShotRegisters()
	if (!Klib(vm).match(name)) {
		vm.errors.MissingLibraryException(name) // Kt should be the last resort
	}
	currentFunction = name
	vm.snapShotManager.populateSnapShotRegister(snapshot)
	vm.pc = oldPc
}