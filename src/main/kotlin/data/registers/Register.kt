package data.registers

open class Registers(
    genRegisters: GeneralRegisters = GeneralRegisters(),
    retRegisters: ReturnRegisters = ReturnRegisters(),
    sysRegisters: SystemRegisters = SystemRegisters(),
) {

    val systemRegisters = sysRegisters
    val generalRegisters = genRegisters
    val returnRegisters = retRegisters
}

