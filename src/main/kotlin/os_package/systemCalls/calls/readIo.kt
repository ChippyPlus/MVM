package os_package.systemCalls.calls

import data.registers.RegisterType
import helpers.writeClosestString
import os_package.systemCalls.SystemCall

fun SystemCall.readIo() = call("readIo") {
	val inp = readln()
	val location = helpers.writeClosestString(inp)
	registers.write(RegisterType.R2, location)
}