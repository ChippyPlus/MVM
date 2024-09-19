package internals.instructions.floats.dataTransfer

import data.registers.enumIdenifiers.SuperRegisterType
import data.registers.enumIdenifiers.toFloatingRegisterType
import helpers.floating.floatingRegisterRead
import helpers.registerWrite

fun itof(destination: SuperRegisterType, value: SuperRegisterType) {
    val fx = floatingRegisterRead(value.toFloatingRegisterType())
    registerWrite(destination, fx.toLong())
}