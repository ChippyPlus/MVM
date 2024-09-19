package internals.instructions.floats.dataTransfer

import data.registers.enumIdenifiers.SuperRegisterType
import data.registers.enumIdenifiers.toFloatingRegisterType
import helpers.floating.floatingRegisterRead
import helpers.registerWrite
import internals.instructions.floats.Floats

fun Floats.itof(destination: SuperRegisterType, value: SuperRegisterType) {
    val fx = floatingRegisterRead(value.toFloatingRegisterType())
    registerWrite(destination, fx.toLong())
}