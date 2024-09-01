package org.example.data.registers

import org.example.data.registers.enumIdenifiers.SystemRegisterType

/**
 * Represents a set of registers used exclusively for system calls.
 *
 * This class provides methods for reading and writing values to the individual system registers (S1-S4).
 *
 * The `S1` register is used to hold the system call number. The remaining registers (S2-S4) are used to pass arguments to system calls.
 */
class SystemRegisters {
    private var s1: UByte = 0u
    private var s2: UByte = 0u
    private var s3: UByte = 0u
    private var s4: UByte = 0u

    /**
     * Reads the value from the specified system call register.
     *
     * @param registers The system call register to read from.
     * @return The value stored in the specified register.
     */
    fun read(registers: SystemRegisterType): UByte {
        return when (registers) {
            SystemRegisterType.S1 -> s1
            SystemRegisterType.S2 -> s2
            SystemRegisterType.S3 -> s3
            SystemRegisterType.S4 -> s4
        }
    }

    /**
     * Writes a value to the specified system call register.
     *
     * @param registers The system call register to write to.
     * @param value The value to write to the register.
     */
    fun write(registers: SystemRegisterType, value: UByte) {
        when (registers) {
            SystemRegisterType.S1 -> s1 = value
            SystemRegisterType.S2 -> s2 = value
            SystemRegisterType.S3 -> s3 = value
            SystemRegisterType.S4 -> s4 = value
        }
    }
}