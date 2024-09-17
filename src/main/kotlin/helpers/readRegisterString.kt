package helpers

import data.memory.MemoryAddress
import data.registers.enumIdenifiers.SuperRegisterType
import internalMemory

fun readRegisterString(register: SuperRegisterType): String {
    var index = 0
    var string = ""
    while (true) {
        val byte = internalMemory.read(MemoryAddress(fullRegisterRead(register) + index))
        if (byte.value == 0L) break
        string += byte.value!!.toInt().toChar()
        index++
    }
    return string
}