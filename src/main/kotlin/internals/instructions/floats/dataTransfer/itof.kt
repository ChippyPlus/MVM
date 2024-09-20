package internals.instructions.floats.dataTransfer

import data.registers.enumIdenifiers.FloatingRegisterType
import data.registers.enumIdenifiers.SuperRegisterType
import helpers.floating.floatingRegisterRead
import helpers.registerWrite
import internals.instructions.floats.Floats

fun Floats.itof(destination: SuperRegisterType, value: FloatingRegisterType) {
    val fx = floatingRegisterRead(value)
    registerWrite(destination, fx.toBits())
}