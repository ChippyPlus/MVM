package data.registers

import data.registers.enumIdenifiers.SystemRegisterType
import errors
import helpers.toSuperRegisterType
import kotlin.system.exitProcess

class SystemRegisters {
    var s1: Long? = null
    var s2: Long? = null
    var s3: Long? = null
    var s4: Long? = null

    fun read(registers: SystemRegisterType): Long {
        try {
            return when (registers) {
                SystemRegisterType.S1 -> s1!!
                SystemRegisterType.S2 -> s2!!
                SystemRegisterType.S3 -> s3!!
                SystemRegisterType.S4 -> s4!!
            }
        } catch (e: NullPointerException) {
            errors.NullRegisterException(registers.toString().toSuperRegisterType())
            exitProcess(11) // To satisfy the compiler. This shouldn't trigger
        }
    }

    fun write(registers: SystemRegisterType, value: Long) {
        when (registers) {
            SystemRegisterType.S1 -> s1 = value
            SystemRegisterType.S2 -> s2 = value
            SystemRegisterType.S3 -> s3 = value
            SystemRegisterType.S4 -> s4 = value
        }
    }
}