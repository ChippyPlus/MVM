package kernel.process

import engine.execution.InstructData
import helpers.RuntimeStates

data class KThread(val id: UInt) {
	var currentInstruction: InstructData = InstructData(name = "init", arrayOf())
	var state: RuntimeStates = RuntimeStates.RUNNING

}