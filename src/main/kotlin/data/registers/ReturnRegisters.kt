package org.example.data.registers

import org.example.data.registers.enumIdenifiers.ReturnRegisterType


/**
 * Represents a set of registers used to store the results of operations and system calls.
 *
 * This class provides methods for reading and writing values to the individual return registers (R2 and R4).
 *
 * The `R2` and `R4` registers are used to store specific results, such as the sum of an addition or the number of bytes read from a file.
 *
 * **Note:**  `R1` and `R3` are deprecated. The KVM now relies on runtime exceptions for error handling.
 */
class ReturnRegisters {
    private var r1: UByte = 0u
    private var r2: UByte = 0u
    private var r3: UByte = 0u
    private var r4: UByte = 0u

    /**
     * Reads the value from the specified return register.
     *
     * @param registers The return register to read from.
     * @return The value stored in the specified register.
     */
    fun read(registers: ReturnRegisterType): UByte {
        return when (registers) {
            ReturnRegisterType.R1 -> r1
            ReturnRegisterType.R2 -> r2
            ReturnRegisterType.R3 -> r3
            ReturnRegisterType.R4 -> r4
        }
    }

    /**
     * Writes a value to the specified return register.
     *
     * @param registers The return register to write to.
     * @param value The value to write to the register.
     */
    fun write(registers: ReturnRegisterType, value: UByte) {
        when (registers) {
            ReturnRegisterType.R1 -> r1 = value
            ReturnRegisterType.R2 -> r2 = value
            ReturnRegisterType.R3 -> r3 = value
            ReturnRegisterType.R4 -> r4 = value
        }
    }

}