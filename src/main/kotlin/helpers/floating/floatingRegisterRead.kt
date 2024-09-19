package helpers.floating

import data.registers.enumIdenifiers.FloatingRegisterType
import floatingRegisters

fun floatingRegisterRead(registerType: FloatingRegisterType): Double {
    return when (registerType) {
        FloatingRegisterType.F1 -> floatingRegisters.read(registerType)
        FloatingRegisterType.F2 -> floatingRegisters.read(registerType)
        FloatingRegisterType.F3 -> floatingRegisters.read(registerType)
        FloatingRegisterType.F4 -> floatingRegisters.read(registerType)
    }
}
