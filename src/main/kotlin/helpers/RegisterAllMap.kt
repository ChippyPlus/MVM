package org.example.helpers

import org.example.data.registers.enumIdenifiers.GeneralRegisterType
import org.example.data.registers.enumIdenifiers.ReturnRegisterType
import org.example.data.registers.enumIdenifiers.SuperRegisterType
import org.example.data.registers.enumIdenifiers.SystemRegisterType
import org.example.generalRegisters
import org.example.returnRegisters
import org.example.systemRegisters

fun RegisterAllMap(): MutableMap<SuperRegisterType, UByte> {
    val dict = mutableMapOf<SuperRegisterType, UByte>()
    for (type in SuperRegisterType.entries) {
        when (type) {
            SuperRegisterType.G1 -> dict[SuperRegisterType.G1] = generalRegisters.read(GeneralRegisterType.G1)
            SuperRegisterType.G2 -> dict[SuperRegisterType.G2] = generalRegisters.read(GeneralRegisterType.G2)
            SuperRegisterType.G3 -> dict[SuperRegisterType.G3] = generalRegisters.read(GeneralRegisterType.G3)
            SuperRegisterType.G4 -> dict[SuperRegisterType.G4] = generalRegisters.read(GeneralRegisterType.G4)
            SuperRegisterType.S1 -> dict[SuperRegisterType.S1] = systemRegisters.read(SystemRegisterType.S1)
            SuperRegisterType.S2 -> dict[SuperRegisterType.S2] = systemRegisters.read(SystemRegisterType.S2)
            SuperRegisterType.S3 -> dict[SuperRegisterType.S3] = systemRegisters.read(SystemRegisterType.S3)
            SuperRegisterType.S4 -> dict[SuperRegisterType.S2] = systemRegisters.read(SystemRegisterType.S4)
            SuperRegisterType.R1 -> dict[SuperRegisterType.R1] = returnRegisters.read(ReturnRegisterType.R1)
            SuperRegisterType.R2 -> dict[SuperRegisterType.R2] = returnRegisters.read(ReturnRegisterType.R2)
            SuperRegisterType.R3 -> dict[SuperRegisterType.R3] = returnRegisters.read(ReturnRegisterType.R3)
            SuperRegisterType.R4 -> dict[SuperRegisterType.R4] = returnRegisters.read(ReturnRegisterType.R4)
        }
    }
    return dict
}
