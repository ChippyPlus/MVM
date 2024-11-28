package os_package.systemCalls.calls

import data.registers.RegisterType
import helpers.readRegisterString
import os_package.systemCalls.SystemCall


fun SystemCall.readFile(nameX: RegisterType) = call("readFile") {
	val name = helpers.readRegisterString(nameX)
//	val out = (vm.vfs.read(name) ?: errors.FileNotFoundException(name)) as String
//	val spot = helpers.writeClosestString(out)
//	registers.write(RegisterType.R2, spot)
	TODO("Bring back VFS")

}
