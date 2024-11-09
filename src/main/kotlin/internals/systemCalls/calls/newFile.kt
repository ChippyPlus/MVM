package internals.systemCalls.calls

import data.registers.RegisterType
import errors
import helpers.readRegisterString
import internals.systemCalls.SystemCall
import vm

fun SystemCall.newFile(name: RegisterType) = call("newFile") {
	vm.vfs.new(readRegisterString(name)) ?: errors.FileAlreadyExistsException(readRegisterString(name))
}