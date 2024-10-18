package internals.instructions.stackOperations

import data.stack.FixedStack


/**
 * Represents the stack operations unit within the virtual machine.
 *
 * This class provides functions for managing the stack, including pushing, popping, and peeking at elements.
 *
 * @property limit The maximum size of the stack.
 */
class StackOperations(limit: Int) {
	val internalStack = FixedStack(limit)
}