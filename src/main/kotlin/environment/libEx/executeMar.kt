package environment.libEx

import engine.execution.Execute
import engine.parser
import environment.ExecuteLib
import environment.snapShotManager
import vm
import java.io.File

fun ExecuteLib.executeMar(file: File) {
	val oldPc = vm.pc
	val snapshot = snapShotManager.fullSnapshot()
	vm.pc - 2
	Execute().run(parser(file.readLines().subList(0, file.readLines().size)))
	snapShotManager.populateSnapShot(snapshot)
	vm.pc = oldPc
}