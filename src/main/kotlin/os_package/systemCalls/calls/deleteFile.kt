package os_package.systemCalls.calls

import data.registers.RegisterType
import helpers.readRegisterString
import os_package.systemCalls.SystemCall

fun SystemCall.deleteFile(pathX: RegisterType) = call("deleteFile") {
	val path = helpers.readRegisterString(pathX)
	TODO("Bring back VFS")
//	vm.vfs.delete(path) ?: errors.FileNotFoundException(path)
}