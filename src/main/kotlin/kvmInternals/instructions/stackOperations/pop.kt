package org.example.kvmInternals.instructions.stackOperations

import org.example.data.registers.enumIdenifiers.SuperRegisterType
import org.example.helpers.fullRegisterWrite


fun StackOperations.pop(destination: SuperRegisterType) {
    val value = internalStack.pop()
    fullRegisterWrite(destination, value)
}