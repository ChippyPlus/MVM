package kernel.libEx

import kernel.ExecuteLib
import kernel.SnapShotManagerLegacy
import kernel.UnstableSnapShots
import kilb.Klib

@OptIn(UnstableSnapShots::class)
fun ExecuteLib.executeKt(name: String) {
	val smLegacy = SnapShotManagerLegacy(vm)
	val oldPc = vm.pc
	val snapshot = smLegacy.snapShotRegisters()
	if (!Klib(vm).match(name)) {
		vm.errors.missingLibraryException(name) // Kt should be the last resort
	}
	currentFunction = name
	smLegacy.populateSnapShotRegister(snapshot)
	vm.pc = oldPc
}