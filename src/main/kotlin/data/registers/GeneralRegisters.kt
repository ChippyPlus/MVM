package org.example.data.registers

import org.example.data.registers.enumIdenifiers.GeneralRegisterType

class GeneralRegisters {
    private var g1: UByte = 0u
    private var g2: UByte = 0u
    private var g3: UByte = 0u
    private var g4: UByte = 0u

    fun read(registers: GeneralRegisterType): UByte {
        return when (registers) {
            GeneralRegisterType.G1 -> g1
            GeneralRegisterType.G2 -> g2
            GeneralRegisterType.G3 -> g3
            GeneralRegisterType.G4 -> g4
        }
    }
    fun write(registers: GeneralRegisterType, value: UByte) {
        when (registers) {
            GeneralRegisterType.G1 -> g1 = value
            GeneralRegisterType.G2 -> g2 = value
            GeneralRegisterType.G3 -> g3 = value
            GeneralRegisterType.G4 -> g4 = value
        }
    }
}