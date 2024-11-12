package internals.systemCalls.calls

import data.registers.RegisterType
import helpers.readRegisterString
import internals.systemCalls.SystemCall

fun SystemCall.deleteFile(path: RegisterType) = call("deleteFile") {
	val _path = helpers.readRegisterString(path)
	vm.vfs.delete(_path) ?: errors.FileNotFoundException(_path)
}