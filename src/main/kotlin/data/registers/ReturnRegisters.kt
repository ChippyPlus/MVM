package data.registers

import errors
import data.registers.enumIdenifiers.ReturnRegisterType
import helpers.toSuperRegisterType
import kotlin.system.exitProcess


/**
 * Represents a set of registers used to store the results of operation and system calls.
 *
 * This class provides methods for reading and writing values to the individual return registers (R2 and R4).
 *
 * The `R2` and `R4` registers are used to store specific results, such as the sum of an addition or the number of bytes read from a file.
 *
 * **Note: **  `R1` and `R3` are deprecated. The KVM now relies on runtime exceptions for error handling.
 */
class ReturnRegisters {
    var r1: Long? = null
    var r2: Long? = null
    var r3: Long? = null
    var r4: Long? = null

    /**
     * Reads the value from the specified return register.
     *
     * @param registers The return register to read from.
     * @return The value stored in the specified register.
     */
    fun read(registers: ReturnRegisterType): Long {
        try {
            return when (registers) {
                ReturnRegisterType.R1 -> r1!!
                ReturnRegisterType.R2 -> r2!!
                ReturnRegisterType.R3 -> r3!!
                ReturnRegisterType.R4 -> r4!!
            }
        } catch (e: NullPointerException) {
            errors.NullRegisterException(registers.toString().toSuperRegisterType())
            exitProcess(11) // To satisfy the compiler. This shouldn't trigger
        }
    }

    /**
     * Writes a value to the specified return register.
     *
     * @param registers The return register to write to.
     * @param value The value to write to the register.
     */
    fun write(registers: ReturnRegisterType, value: Long) {
        when (registers) {
            ReturnRegisterType.R1 -> r1 = value
            ReturnRegisterType.R2 -> r2 = value
            ReturnRegisterType.R3 -> r3 = value
            ReturnRegisterType.R4 -> r4 = value
        }
    }

}