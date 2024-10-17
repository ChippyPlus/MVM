package internals.systemCalls.calls

import data.registers.enumIdenifiers.SuperRegisterType
import errors
import helpers.readRegisterString
import internals.systemCalls.SystemCall
import vm

fun SystemCall.writeFile(path: SuperRegisterType, contentAddress: SuperRegisterType) {
	val _path = readRegisterString(path)
	val _contentAddress = readRegisterString(contentAddress)
	vm.vfs.write(name = _path, content = _contentAddress) ?: errors.FileNotFoundException(_path)
}