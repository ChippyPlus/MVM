package helpers.floating

import data.registers.enumIdenifiers.FloatingRegisterType
import floatingRegisters

fun floatingRegisterRead(register: FloatingRegisterType): Double {
    return when (register) {
        FloatingRegisterType.F1 -> floatingRegisters.read(register)
        FloatingRegisterType.F2 -> floatingRegisters.read(register)
        FloatingRegisterType.F3 -> floatingRegisters.read(register)
        FloatingRegisterType.F4 -> floatingRegisters.read(register)
    }
}
