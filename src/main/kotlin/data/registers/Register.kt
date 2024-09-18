package data.registers

/**
 * Represents a collection of all register types in the virtual machine.
 *
 * @property generalRegisters The general-purpose registers.
 * @property returnRegisters The return registers.
 * @property systemRegisters The system registers.
 */
open class Registers(
    genRegisters: GeneralRegisters = GeneralRegisters(),
    retRegisters: ReturnRegisters = ReturnRegisters(),
    sysRegisters: SystemRegisters = SystemRegisters(),
) {

    val systemRegisters = sysRegisters
    val generalRegisters = genRegisters
    val returnRegisters = retRegisters
}