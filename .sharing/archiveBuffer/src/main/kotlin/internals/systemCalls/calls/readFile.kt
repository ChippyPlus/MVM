package internals.systemCalls.calls

import data.registers.RegisterType
import helpers.readRegisterString
import helpers.writeClosestString
import internals.systemCalls.SystemCall


fun SystemCall.readFile(nameX: RegisterType) = call("readFile") {
	val name = helpers.readRegisterString(nameX)
	val out = (vm.vfs.read(name) ?: errors.FileNotFoundException(name)) as String
	val spot = helpers.writeClosestString(out)
	registers.write(RegisterType.R2, spot)
}
