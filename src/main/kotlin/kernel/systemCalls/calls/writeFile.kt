package kernel.systemCalls.calls

import data.registers.RegisterType
import helpers.readRegisterString
import kernel.systemCalls.SystemCall

fun SystemCall.writeFile(pathX: RegisterType, contentAddressX: RegisterType) = call("writeFile") {
	val path = helpers.readRegisterString(pathX)
	val contentAddress = helpers.readRegisterString(contentAddressX)
//	vm.vfs.write(name = path, content = contentAddress) ?: errors.FileNotFoundException(path)
	TODO("Bring back VFS")

}