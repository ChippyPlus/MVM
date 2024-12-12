package kernel.libEx

import engine.parserReturn
import internals.Vm
import kernel.ExecuteLib
import kernel.process.KProcess
import os
import java.io.File

suspend fun ExecuteLib.executeMar(file: File) {

	val localKp = KProcess(Vm(), file)
	os.snapShotManager.snapShotRegisters(kp)

	val oldInstructs = kp.instructionMemory

	localKp.instructionMemory = parserReturn(localKp.vm, file.readLines())
	localKp.notifyOS()

	kp.instructionMemory = oldInstructs

	os.snapShotManager.populateSnapShotRegister(kp)
}