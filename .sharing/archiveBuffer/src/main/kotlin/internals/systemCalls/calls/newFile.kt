package internals.systemCalls.calls

import data.registers.RegisterType
import helpers.readRegisterString
import internals.systemCalls.SystemCall

fun SystemCall.newFile(name: RegisterType) = call("newFile") {
	vm.vfs.new(helpers.readRegisterString(name)) ?: errors.FileAlreadyExistsException(helpers.readRegisterString(name))
}