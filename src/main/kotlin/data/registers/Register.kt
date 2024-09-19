package data.registers

/**
 * Represents a collection of all register types in the virtual machine.
 *
 * @property generalRegisters The general-purpose registers.
 * @property returnRegisters The return registers.
 * @property systemRegisters The system registers.
 */
open class Registers {

    val systemRegisters = SystemRegisters()
    val generalRegisters = GeneralRegisters()
    val returnRegisters = ReturnRegisters()
    val floatingRegisters = FloatingRegisters()
}