package kernel.libEx

import engine.execution.Execute
import engine.parserReturn
import kernel.ExecuteLib
import java.io.File

fun ExecuteLib.executeMar(file: File) {
	val vm = vm
	val oldPC = kp.vm.pc
	val oldInstructs = kp.instructionMemory
	val snapshot = vm.snapShotManager.snapShotRegisters()
	vm.pc - 2
	val instructions = parserReturn(vm, file.readLines())
	Execute(kp).run(instructions)
	vm.snapShotManager.populateSnapShotRegister(snapshot)
	kp.vm.pc = oldPC
	kp.instructionMemory = oldInstructs
}