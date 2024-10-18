package internals.systemCalls.calls

import data.registers.RegisterType
import errors
import helpers.readRegisterString
import internals.systemCalls.SystemCall
import vm

fun SystemCall.deleteFile(path: RegisterType) {
	val _path = readRegisterString(path)
	vm.vfs.delete(_path) ?: errors.FileNotFoundException(_path)
}