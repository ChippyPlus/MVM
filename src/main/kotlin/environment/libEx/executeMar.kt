package environment.libEx

import engine.execution.Execute
import engine.parser
import environment.ExecuteLib
import java.io.File

suspend fun ExecuteLib.executeMar(file: File) {
	val oldPc = vm.pc
	val snapshot = vm.snapShotManager.snapShotRegisters()
	vm.pc - 2
	Execute(vm).run(parser(vm, file.readLines()))
	vm.snapShotManager.populateSnapShotRegister(snapshot)
	vm.pc = oldPc
}