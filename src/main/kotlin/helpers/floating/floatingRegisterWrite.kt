package helpers.floating

import data.registers.enumIdenifiers.FloatingRegisterType
import data.registers.enumIdenifiers.ReturnRegisterType
import floatingRegisters
import returnRegisters

fun floatingRegisterWrite(register: FloatingRegisterType, value: Double) {
    when (register) {
        FloatingRegisterType.F1 -> floatingRegisters.write(register, value)
        FloatingRegisterType.F2 -> floatingRegisters.write(register, value)
        FloatingRegisterType.F3 -> floatingRegisters.write(register, value)
        FloatingRegisterType.F4 -> floatingRegisters.write(register, value)
        FloatingRegisterType.R1 -> returnRegisters.write(ReturnRegisterType.R1, value)
    }
}