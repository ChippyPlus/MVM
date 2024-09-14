package org.example.kvmInternals.instructions.stackOperations

import org.example.data.stack.FixedStack
import java.util.*


/**
 * **Stack Operations: **
 *
 * * `PUSH` - Push a value onto the stack.
 * * `POP`  - Pop a value from the stack.
 *

 */
class StackOperations(limit: Int) {
    val internalStack = FixedStack(limit)
}