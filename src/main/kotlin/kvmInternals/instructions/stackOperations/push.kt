package org.example.kvmInternals.instructions.stackOperations


/**
 * Pushes a value onto the stack.
 *
 * @param value The value to push.
 */

fun StackOperations.push(value: Int) {
    internalStack.push(value)
}