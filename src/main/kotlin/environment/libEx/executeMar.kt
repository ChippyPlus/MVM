package environment.libEx

import engine.execution.Execute
import engine.parser
import environment.ExecuteLib
import environment.snapShotManager
import java.io.File

fun ExecuteLib.executeMar(file: File) {
	val oldPc = vm.pc
	val snapshot = snapShotManager.snapShotRegisters()
	vm.pc - 2
	Execute(vm).run(parser(vm, file.readLines()))
	snapShotManager.populateSnapShotRegister(snapshot)
	vm.pc = oldPc
}