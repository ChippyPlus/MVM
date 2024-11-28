package os_package

import internals.Vm
import os_package.libEx.executeKt
import os_package.libEx.executeMar
import os_package.libEx.findMarLib
import java.io.File


class ExecuteLib(val vm: Vm) {
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


