package kernel.libEx

import engine.execution.Execute
import engine.parserReturn
import environment.reflection.reflection
import kernel.ExecuteLib
import java.io.File

fun ExecuteLib.executeMar(file: File) {
	val vm = vm
	val oldPc = vm.pc
	val snapshot = vm.snapShotManager.snapShotRegisters()
	vm.pc - 2

	val instructions = parserReturn(vm, file.readLines())

	Execute(reflection.groupTrackedVmByVm()[vm]!!).run(instructions)
	vm.snapShotManager.populateSnapShotRegister(snapshot)
	vm.pc = oldPc
}