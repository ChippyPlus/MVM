package environment.libEx

import engine.execution.Execute
import engine.parser
import environment.ExecuteLib
import vm
import java.io.File

fun ExecuteLib.executeMar(file: File) {
	val oldPc = vm.pc
	val snapshot = sm.fullSnapshot()
	vm.pc - 2
	Execute().run(parser(file.readLines().subList(0, file.readLines().size)))
	sm.populateSnapShot(snapshot)
	vm.pc = oldPc
}