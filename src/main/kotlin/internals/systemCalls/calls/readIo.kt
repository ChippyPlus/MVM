package internals.systemCalls.calls

import data.registers.enumIdenifiers.SuperRegisterType
import errors
import helpers.fullRegisterWrite
import helpers.writeClosestString
import internals.systemCalls.SystemCall

fun SystemCall.readIo() = try {
    val inp = readln()
    val location = writeClosestString(inp)
    fullRegisterWrite(SuperRegisterType.R2, location)
} catch (e: Exception) {
    errors.SystemCallGeneralException("readIo")
}