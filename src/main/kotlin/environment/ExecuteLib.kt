package environment

import engine.execution.InstructData
import environment.libEx.executeKt
import environment.libEx.executeMar
import environment.libEx.findMarLib
import internals.Vm
import java.io.File


class ExecuteLib(val vm: Vm) {
	val parsedMarFiles = mutableMapOf<String, List<InstructData>>()
	var currentFunction = ""
	var enabledFunction = false

	suspend fun execute(name: String) {
		if (findMarLib(name) != null) {
			val file = File(findMarLib(name)!!)
			enabledFunction = true
			currentFunction = File(findMarLib(name)!!).name
			executeMar(file)
			enabledFunction = false
		} else {
			executeKt(name)
		}
	}
}


