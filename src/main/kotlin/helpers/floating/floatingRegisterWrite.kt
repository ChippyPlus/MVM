package helpers.floating

import data.registers.enumIdenifiers.FloatingRegisterType
import floatingRegisters

fun floatingRegisterWrite(register: FloatingRegisterType, value: Double) {
    when (register) {
        FloatingRegisterType.F1 -> floatingRegisters.write(register, value)
        FloatingRegisterType.F2 -> floatingRegisters.write(register, value)
        FloatingRegisterType.F3 -> floatingRegisters.write(register, value)
        FloatingRegisterType.F4 -> floatingRegisters.write(register, value)
    }
}