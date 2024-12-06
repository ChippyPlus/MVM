package kernel.systemCalls.calls

import data.registers.RegisterType
import helpers.writeClosestString
import kernel.systemCalls.SystemCall

fun SystemCall.readIo() = call("readIo") {
	val inp = readln()
	val location = helpers.writeClosestString(inp)
	registers.write(RegisterType.R2, location)
}