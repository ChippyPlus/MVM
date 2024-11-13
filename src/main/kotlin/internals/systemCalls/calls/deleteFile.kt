package internals.systemCalls.calls

import data.registers.RegisterType
import helpers.readRegisterString
import internals.systemCalls.SystemCall

fun SystemCall.deleteFile(pathX: RegisterType) = call("deleteFile") {
	val path = helpers.readRegisterString(pathX)
	vm.vfs.delete(path) ?: errors.FileNotFoundException(path)
}