package org.example.helpers

import org.example.data.registers.enumIdenifiers.SuperRegisterType
import org.example.data.registers.enumIdenifiers.toGeneralRegisterType
import org.example.data.registers.enumIdenifiers.toReturnRegisterType
import org.example.data.registers.enumIdenifiers.toSystemRegisterType
import org.example.generalRegisters
import org.example.returnRegisters
import org.example.systemRegisters

fun fullRegisterWrite(register: SuperRegisterType,value: Long) {
    when (register) {
        SuperRegisterType.G1 -> generalRegisters.write(register.toGeneralRegisterType(), value)
        SuperRegisterType.G2 -> generalRegisters.write(register.toGeneralRegisterType(), value)
        SuperRegisterType.G3 -> generalRegisters.write(register.toGeneralRegisterType(), value)
        SuperRegisterType.G4 -> generalRegisters.write(register.toGeneralRegisterType(), value)
        SuperRegisterType.S1 -> systemRegisters.write(register.toSystemRegisterType(), value)
        SuperRegisterType.S2 -> systemRegisters.write(register.toSystemRegisterType(), value)
        SuperRegisterType.S3 -> systemRegisters.write(register.toSystemRegisterType(), value)
        SuperRegisterType.S4 -> systemRegisters.write(register.toSystemRegisterType(), value)
        SuperRegisterType.R1 -> returnRegisters.write(register.toReturnRegisterType(), value)
        SuperRegisterType.R2 -> returnRegisters.write(register.toReturnRegisterType(), value)
        SuperRegisterType.R3 -> returnRegisters.write(register.toReturnRegisterType(), value)
        SuperRegisterType.R4 -> returnRegisters.write(register.toReturnRegisterType(), value)
    }
}