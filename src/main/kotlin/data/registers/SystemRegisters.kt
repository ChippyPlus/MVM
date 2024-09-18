package data.registers

import data.registers.enumIdenifiers.SystemRegisterType
import errors
import helpers.toSuperRegisterType
import kotlin.system.exitProcess

/**
 * Represents the set of system registers in the virtual machine.
 *
 * System registers are primarily used for handling system calls and storing system-related information.
 */
class SystemRegisters {
    var s1: Long? = null
    var s2: Long? = null
    var s3: Long? = null
    var s4: Long? = null

    /**
     * Reads the value from the specified system register.
     *
     * @param registers The [SystemRegisterType] to read from.
     * @return The value stored in the register as a [Long].
     * @throws NullRegisterException If the register has not been initialised (has a null value).
     */
    fun read(registers: SystemRegisterType): Long {
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
     * Writes a value to the specified system register.
     *
     * @param registers The [SystemRegisterType] to write to.
     * @param value The [Long] value to write to the register.
     */
    fun write(registers: SystemRegisterType, value: Long) {
        when (registers) {
            SystemRegisterType.S1 -> s1 = value
            SystemRegisterType.S2 -> s2 = value
            SystemRegisterType.S3 -> s3 = value
            SystemRegisterType.S4 -> s4 = value
        }
    }
}