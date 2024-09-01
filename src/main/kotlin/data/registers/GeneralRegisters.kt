package org.example.data.registers

import org.example.data.registers.enumIdenifiers.GeneralRegisterType


/**
 * Represents a set of general-purpose registers used for calculations and data manipulation within the virtual machine.
 *
 * This class provides methods for reading and writing values to the individual general-purpose registers (G1-G4).
 *
 * General-purpose registers are used for storing operands, intermediate results, and other values during program execution.
 */
class GeneralRegisters {
    private var g1 = 0
    private var g2 = 0
    private var g3 = 0
    private var g4 = 0

    /**
     * Reads the value from the specified general purpose register.
     *
     * @param registers The general purpose register to read from.
     * @return The value stored in the specified register.
     */
    fun read(registers: GeneralRegisterType): Int {
        return when (registers) {
            GeneralRegisterType.G1 -> g1
            GeneralRegisterType.G2 -> g2
            GeneralRegisterType.G3 -> g3
            GeneralRegisterType.G4 -> g4
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