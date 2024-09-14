package internals.instructions.dataTransfer

import org.example.data.registers.enumIdenifiers.SuperRegisterType
import org.example.helpers.fullRegisterRead
import org.example.helpers.fullRegisterWrite

fun DataTransfer.cpy(register1: SuperRegisterType, register2: SuperRegisterType) {
    val a = fullRegisterRead(register1)
    fullRegisterWrite(register2, a)
}