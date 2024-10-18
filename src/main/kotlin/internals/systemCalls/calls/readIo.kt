package internals.systemCalls.calls

import data.registers.RegisterType
import errors
import helpers.writeClosestString
import internals.systemCalls.SystemCall
import registers

fun SystemCall.readIo() = try {
    val inp = readln()
    val location = writeClosestString(inp)
    registers.write(RegisterType.R2, location)
} catch (e: Exception) {
    errors.SystemCallGeneralException("readIo")
}