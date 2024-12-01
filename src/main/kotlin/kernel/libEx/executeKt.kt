package kernel.libEx

import kernel.ExecuteLib
import kilb.Klib

fun ExecuteLib.executeKt(name: String) {
	val oldPc = vm.pc
	val snapshot = vm.snapShotManager.snapShotRegisters()
	if (!Klib(vm).match(name)) {
		vm.errors.missingLibraryException(name) // Kt should be the last resort
	}
	currentFunction = name
	vm.snapShotManager.populateSnapShotRegister(snapshot)
	vm.pc = oldPc
}