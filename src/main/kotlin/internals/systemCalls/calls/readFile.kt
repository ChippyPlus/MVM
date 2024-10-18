package internals.systemCalls.calls

import data.registers.RegisterType
import errors
import helpers.readRegisterString
import helpers.writeClosestString
import internals.systemCalls.SystemCall
import registers
import vm


fun SystemCall.readFile(name: RegisterType) {
	val _name = readRegisterString(name)
	val out = (vm.vfs.read(_name) ?: errors.FileNotFoundException(_name)) as String
	val spot = writeClosestString(out)
	registers.write(RegisterType.R2, spot)
}
