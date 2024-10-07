package data.registers

import data.registers.enumIdenifiers.ReturnRegisterType
import errors
import helpers.toSuperRegisterType
import kotlin.system.exitProcess

/**
 * Represents the set of return registers in the virtual machine.
 *
 * Return registers are primarily used to store results from operations & system calls
 */
class ReturnRegisters {
    var r1: Long? = null
    var r2: Long? = null
    var r3: Long? = null
    var r4: Long? = null

    /**
     * Reads the value from the specified return register.
     *
     * @param registers The [ReturnRegisterType] to read from.
     * @return The value stored in the register as a [Long].
     * @throws NullRegisterException If the register has not been initialised (has a null value).
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


    fun readUnsafe(registers: ReturnRegisterType): Long? {
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
     * @param registers The [ReturnRegisterType] to write to.
     * @param value The [Long] value to write to the register.
     */
    fun write(registers: ReturnRegisterType, value: Long?) {
        when (registers) {
            ReturnRegisterType.R1 -> r1 = value
            ReturnRegisterType.R2 -> r2 = value
            ReturnRegisterType.R3 -> r3 = value
            ReturnRegisterType.R4 -> r4 = value
        }
    }
}