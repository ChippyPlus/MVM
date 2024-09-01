package org.example.kvmInternals.instructions.stackOperations

import org.example.data.registers.enumIdenifiers.SuperRegisterType
import org.example.helpers.fullRegisterWrite


fun StackOperations.peek(destination: SuperRegisterType) {
    val value = internalStack.peek()
    fullRegisterWrite(destination, value)
}