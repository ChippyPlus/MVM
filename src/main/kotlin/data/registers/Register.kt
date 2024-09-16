package data.registers

import data.registers.GeneralRegisters
import data.registers.ReturnRegisters
import data.registers.SystemRegisters

open class Registers(
    genRegisters: GeneralRegisters = GeneralRegisters(),
    retRegisters: ReturnRegisters = ReturnRegisters(),
    sysRegisters: SystemRegisters = SystemRegisters(),
) {

    val systemRegisters = sysRegisters
    val generalRegisters = genRegisters
    val returnRegisters = retRegisters
}

