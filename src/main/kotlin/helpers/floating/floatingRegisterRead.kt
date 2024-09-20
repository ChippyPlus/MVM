package helpers.floating

import data.registers.enumIdenifiers.FloatingRegisterType
import data.registers.enumIdenifiers.ReturnRegisterType
import floatingRegisters
import returnRegisters

fun floatingRegisterRead(register: FloatingRegisterType): Double {
    return when (register) {
        FloatingRegisterType.F1 -> floatingRegisters.read(register)
        FloatingRegisterType.F2 -> floatingRegisters.read(register)
        FloatingRegisterType.F3 -> floatingRegisters.read(register)
        FloatingRegisterType.F4 -> floatingRegisters.read(register)
        FloatingRegisterType.R1 -> returnRegisters.read(ReturnRegisterType.R1) as Double
    }
}
