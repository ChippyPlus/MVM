package org.example.data.registers

open class Registers(
    private val genRegisters: GeneralRegisters = GeneralRegisters(),
    private val retRegisters: ReturnRegisters = ReturnRegisters(),
    private val sysRegisters: SystemRegisters = SystemRegisters(),
) {

    val systemRegisters = sysRegisters
    val generalRegisters = genRegisters
    val returnRegisters = retRegisters
}

