package internals.instructions.stackOperations

import org.example.data.stack.FixedStack



class StackOperations(limit: Int) {
    val internalStack = FixedStack(limit)
}