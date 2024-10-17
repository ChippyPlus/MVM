package internals.systemCalls.calls

import data.registers.enumIdenifiers.SuperRegisterType
import errors
import helpers.readRegisterString
import internals.systemCalls.SystemCall
import vm

fun SystemCall.newFile(name: SuperRegisterType) =
	vm.vfs.new(readRegisterString(name)) ?: errors.FileAlreadyExistsException(readRegisterString(name))
