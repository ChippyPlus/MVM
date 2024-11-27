package environment.libEx

import engine.execution.Execute
import engine.parserReturn
import environment.ExecuteLib
import environment.reflection.reflection
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