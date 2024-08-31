package org.example.data.registers

import org.example.data.registers.enumIdenifiers.ReturnRegisterType

class ReturnRegisters {
    private var r1: UByte = 0u
    private var r2: UByte = 0u
    private var r3: UByte = 0u
    private var r4: UByte = 0u
    fun read(registers: ReturnRegisterType): UByte {
        return when (registers) {
            ReturnRegisterType.R1 -> r1
            ReturnRegisterType.R2 -> r2
            ReturnRegisterType.R3 -> r3
            ReturnRegisterType.R4 -> r4
        }
    }
    fun write(registers: ReturnRegisterType, value: UByte) {
        when (registers) {
            ReturnRegisterType.R1 -> r1 = value
            ReturnRegisterType.R2 -> r2 = value
            ReturnRegisterType.R3 -> r3 = value
            ReturnRegisterType.R4 -> r4 = value
        }
    }

}