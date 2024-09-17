package internals.instructions.stackOperations

import data.stack.FixedStack



class StackOperations(limit: Int) {
    val internalStack = FixedStack(limit)
}