package environment.libEx

import engine.execution.Execute
import environment.ExecuteLib
import environment.reflection.reflection
import java.io.File

suspend fun ExecuteLib.executeMar(file: File) {
	val vm = vm
	val oldPc = vm.pc
	val snapshot = vm.snapShotManager.snapShotRegisters()
	vm.pc - 2
	Execute(reflection.groupTrackedVmByVm()[vm]!!, file).execute()
	vm.snapShotManager.populateSnapShotRegister(snapshot)
	vm.pc = oldPc
}