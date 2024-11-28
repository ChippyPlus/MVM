package os_package.libEx

import engine.execution.Execute
import engine.parserReturn
import environment.reflection.reflection
import os_package.ExecuteLib
import java.io.File

suspend fun ExecuteLib.executeMar(file: File) {
	val vm = vm
	val oldPc = vm.pc
	val snapshot = vm.snapShotManager.snapShotRegisters()
	vm.pc - 2

	val instructions = parserReturn(vm, file.readLines())

	Execute(reflection.groupTrackedVmByVm()[vm]!!).run(instructions)
	vm.snapShotManager.populateSnapShotRegister(snapshot)
	vm.pc = oldPc
}