package org.example.kvmInternals.instructions.stackOperations

import org.example.data.registers.enumIdenifiers.SuperRegisterType
import org.example.helpers.fullRegisterRead


/**
 * Pushes a value onto the stack.
 *
 * @param registerType The value to push.
 */

fun StackOperations.push(registerType: SuperRegisterType) {
    internalStack.push(fullRegisterRead(registerType)!!)
}