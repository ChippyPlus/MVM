package org.example.data.registers

import org.example.data.registers.enumIdenifiers.SystemRegisterType

class SystemRegisters {
    private var s1: UByte = 0u
    private var s2: UByte = 0u
    private var s3: UByte = 0u
    private var s4: UByte = 0u
    fun read(registers: SystemRegisterType): UByte {
        return when (registers) {
            SystemRegisterType.S1 -> s1
            SystemRegisterType.S2 -> s2
            SystemRegisterType.S3 -> s3
            SystemRegisterType.S4 -> s4
        }
    }
    fun write(registers: SystemRegisterType, value: UByte) {
        when (registers) {
            SystemRegisterType.S1 -> s1 = value
            SystemRegisterType.S2 -> s2 = value
            SystemRegisterType.S3 -> s3 = value
            SystemRegisterType.S4 -> s4 = value
        }
    }
}