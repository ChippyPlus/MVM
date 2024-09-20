package data.registers

import data.registers.enumIdenifiers.FloatingRegisterType
import data.registers.enumIdenifiers.ReturnRegisterType
import errors
import helpers.toSuperRegisterType
import returnRegisters
import kotlin.system.exitProcess

class FloatingRegisters {

    var f1: Double? = null
    var f2: Double? = null
    var f3: Double? = null
    var f4: Double? = null

    fun read(registers: FloatingRegisterType): Double {
        try {
            return when (registers) {
                FloatingRegisterType.F1 -> f1!!
                FloatingRegisterType.F2 -> f2!!
                FloatingRegisterType.F3 -> f3!!
                FloatingRegisterType.F4 -> f4!!
                FloatingRegisterType.R1 -> returnRegisters.read(ReturnRegisterType.R1) as Double
            }
        } catch (e: NullPointerException) {
            errors.NullRegisterException(registers.toString().toSuperRegisterType())
            exitProcess(11) // To satisfy the compiler. This shouldn't trigger
        }
    }


    fun write(registers: FloatingRegisterType, value: Double) {
        when (registers) {
            FloatingRegisterType.R1 -> returnRegisters.write(ReturnRegisterType.R1, value)
            FloatingRegisterType.F1 -> f1 = value
            FloatingRegisterType.F2 -> f2 = value
            FloatingRegisterType.F3 -> f3 = value
            FloatingRegisterType.F4 -> f4 = value
        }
    }

}