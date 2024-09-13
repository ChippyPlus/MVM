package org.example.data.registers

import org.example.data.registers.enumIdenifiers.GeneralRegisterType
import org.example.errors
import org.example.helpers.toSuperRegisterType
import kotlin.system.exitProcess


/**
 * Represents a set of general-purpose registers used for calculations and data manipulation within the virtual machine.
 *
 * This class provides methods for reading and writing values to the individual general-purpose registers (G1-G4).
 *
 * General-purpose registers are used for storing operands, intermediate results, and other values during program execution.
 */
class GeneralRegisters {
    var g1: Int? = null
    var g2: Int? = null
    var g3: Int? = null
    var g4: Int? = null

    /**
     * Reads the value from the specified general purpose register.
     *
     * @param registers The general purpose register to read from.
     * @return The value stored in the specified register.
     */
    fun read(registers: GeneralRegisterType): Int {
        try {
            return when (registers) {
                GeneralRegisterType.G1 -> g1!!
                GeneralRegisterType.G2 -> g2!!
                GeneralRegisterType.G3 -> g3!!
                GeneralRegisterType.G4 -> g4!!
            }
        } catch (e: NullPointerException) {
            errors.NullRegisterException(registers.toString().toSuperRegisterType())
            exitProcess(11) // To satisfy the compiler. This shouldn't trigger
        }
    }

    /**
     * Writes a value to the specified general purpose register.
     *
     * @param registers The general purpose register to write to.
     * @param value The value to write to the register.
     */
    fun write(registers: GeneralRegisterType, value: Int) {
        when (registers) {
            GeneralRegisterType.G1 -> g1 = value
            GeneralRegisterType.G2 -> g2 = value
            GeneralRegisterType.G3 -> g3 = value
            GeneralRegisterType.G4 -> g4 = value
        }
    }
}