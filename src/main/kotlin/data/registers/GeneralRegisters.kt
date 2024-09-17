package data.registers

import data.registers.enumIdenifiers.GeneralRegisterType
import errors
import helpers.toSuperRegisterType
import kotlin.system.exitProcess



class GeneralRegisters {
    var g1: Long? = null
    var g2: Long? = null
    var g3: Long? = null
    var g4: Long? = null

    fun read(registers: GeneralRegisterType): Long {
        try {
            return when (registers) {
                GeneralRegisterType.G1 -> g1!!
                GeneralRegisterType.G2 -> g2!!
                GeneralRegisterType.G3 -> g3!!
                GeneralRegisterType.G4 -> g4!!
            }
        } catch (e: NullPointerException) {
            errors.NullRegisterException(registers.toString().toSuperRegisterType())
            exitProcess(11) // To satisfy the compiler. This shouldn't trigger
        }
    }

    fun write(registers: GeneralRegisterType, value: Long) {
        when (registers) {
            GeneralRegisterType.G1 -> g1 = value
            GeneralRegisterType.G2 -> g2 = value
            GeneralRegisterType.G3 -> g3 = value
            GeneralRegisterType.G4 -> g4 = value
        }
    }
}