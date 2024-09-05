package org.example.data.registers

import org.example.data.registers.enumIdenifiers.SystemRegisterType
import org.example.errors
import org.example.helpers.toSuperRegisterType
import kotlin.system.exitProcess

/**
 * Represents a set of registers used exclusively for system calls.
 *
 * This class provides methods for reading and writing values to the individual system registers (S1-S4).
 *
 * The `S1` register is used to hold the system call number. The remaining registers (S2-S4) are used to pass arguments to system calls.
 */
class SystemRegisters {
    private var s1: Int? = null
    private var s2: Int? = null
    private var s3: Int? = null
    private var s4: Int? = null

    /**
     * Reads the value from the specified system call register.
     *
     * @param registers The system call register to read from.
     * @return The value stored in the specified register.
     */
    fun read(registers: SystemRegisterType): Int {
        try {
            return when (registers) {
                SystemRegisterType.S1 -> s1!!
                SystemRegisterType.S2 -> s2!!
                SystemRegisterType.S3 -> s3!!
                SystemRegisterType.S4 -> s4!!
            }
        } catch (e: NullPointerException) {
            errors.NullRegisterException(registers.toString().toSuperRegisterType())
            exitProcess(11) // To satisfy the compiler. This shouldn't trigger
        }
    }

    /**
     * Writes a value to the specified system call register.
     *
     * @param registers The system call register to write to.
     * @param value The value to write to the register.
     */
    fun write(registers: SystemRegisterType, value: Int) {
        when (registers) {
            SystemRegisterType.S1 -> s1 = value
            SystemRegisterType.S2 -> s2 = value
            SystemRegisterType.S3 -> s3 = value
            SystemRegisterType.S4 -> s4 = value
        }
    }
}