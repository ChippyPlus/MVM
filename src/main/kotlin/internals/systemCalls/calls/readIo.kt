package internals.systemCalls.calls

import data.registers.enumIdenifiers.SuperRegisterType
import errors
import helpers.registerWrite
import helpers.writeClosestString
import internals.systemCalls.SystemCall

fun SystemCall.readIo() = try {
    val inp = readln()
    val location = writeClosestString(inp)
    registerWrite(SuperRegisterType.R2, location)
} catch (e: Exception) {
    errors.SystemCallGeneralException("readIo")
}