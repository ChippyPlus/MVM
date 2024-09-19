package internals.instructions.floats.dataTransfer

import data.registers.enumIdenifiers.FloatingRegisterType
import helpers.floating.floatingRegisterWrite
import internals.instructions.floats.Floats

fun Floats.flit(source: FloatingRegisterType, value: Double) {
    floatingRegisterWrite(source, value)
}
