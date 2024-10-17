package internals.systemCalls.calls

import data.registers.enumIdenifiers.SuperRegisterType
import errors
import helpers.readRegisterString
import helpers.registerWrite
import helpers.writeClosestString
import internals.systemCalls.SystemCall
import vm


fun SystemCall.readFile(name: SuperRegisterType) {
	val _name = readRegisterString(name)
	val out = (vm.vfs.read(_name) ?: errors.FileNotFoundException(_name)) as String
	val spot = writeClosestString(out)
	registerWrite(SuperRegisterType.R2, spot)
}
