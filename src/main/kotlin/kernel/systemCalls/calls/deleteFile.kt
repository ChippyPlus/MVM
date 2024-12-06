package kernel.systemCalls.calls

import data.registers.RegisterType
import helpers.readRegisterString
import kernel.systemCalls.SystemCall

fun SystemCall.deleteFile(pathX: RegisterType) = call("deleteFile") {
	val path = helpers.readRegisterString(pathX)
	TODO("Bring back VFS")
//	vm.vfs.delete(path) ?: errors.FileNotFoundException(path)
}