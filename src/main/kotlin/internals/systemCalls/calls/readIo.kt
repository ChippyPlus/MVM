package internals.systemCalls.calls

import data.registers.RegisterType
import helpers.writeClosestString
import internals.systemCalls.SystemCall
import registers

fun SystemCall.readIo() = call("readIo") {
	val inp = readln()
	val location = writeClosestString(inp)
	registers.write(RegisterType.R2, location)
}