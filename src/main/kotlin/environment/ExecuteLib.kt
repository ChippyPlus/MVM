package environment

import environment.libEx.SnapShotManager
import environment.libEx.executeKt
import environment.libEx.executeMar
import environment.libEx.findMarLib
import java.io.File


class ExecuteLib {
	val sm = SnapShotManager()
	var currentFunction = ""
	var enabledFunction = false

	fun execute(name: String) {
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


