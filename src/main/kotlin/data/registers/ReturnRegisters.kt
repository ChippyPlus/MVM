package data.registers

import data.registers.enumIdenifiers.ReturnRegisterType
import errors
import helpers.toSuperRegisterType
import kotlin.system.exitProcess


class ReturnRegisters {
     var r1: Long? = null
     var r2: Long? = null
     var r3: Long? = null
     var r4: Long? = null

    fun read(registers: ReturnRegisterType): Long {
        try {
            return when (registers) {
                ReturnRegisterType.R1 -> r1!!
                ReturnRegisterType.R2 -> r2!!
                ReturnRegisterType.R3 -> r3!!
                ReturnRegisterType.R4 -> r4!!
            }
        } catch (e: NullPointerException) {
            errors.NullRegisterException(registers.toString().toSuperRegisterType())
            exitProcess(11) // To satisfy the compiler. This shouldn't trigger
        }
    }
    fun write(registers: ReturnRegisterType, value: Long) {
        when (registers) {
            ReturnRegisterType.R1 -> r1 = value
            ReturnRegisterType.R2 -> r2 = value
            ReturnRegisterType.R3 -> r3 = value
            ReturnRegisterType.R4 -> r4 = value
        }
    }

}