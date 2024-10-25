package data.stack

import data.registers.IntelRegisters
import data.registers.intelNames
import environment.errorsCatchable.ErrorType
import environment.errorsCatchable.nonBlockError
import helpers.toDouble
import registers

/**
 * A fixed-size stack implementation using an array.
 *
 * @property maxSize The maximum number of elements that the stack can hold.
 *
 * @constructor Creates a new [FixedStack] with the specified maximum size.
 */
class FixedStack(private val maxSize: Int) {
	private val stack = Array<Long?>(maxSize) { null }
	private var topIndex = -1

	/**
	 * Pushes an element onto the stack.
	 *
	 * @param element The element to push onto the stack.
	 * @throws StackOverflowException If the stack is full.
	 */
	fun push(element: Long) {
		if (isFull()) {
			registers.write(intelNames[IntelRegisters.ENSF], false.toDouble())
			nonBlockError(ErrorType.STACK_OVERFLOW)

			return
		}
		topIndex++
		stack[topIndex] = element
	}

	/**
	 * Pops the top element from the stack and returns it.
	 *
	 * @return The popped element.
	 * @throws EmptyStackException If the stack is empty.
	 */
	fun pop(): Long {
		if (isEmpty()) {
			registers.write(intelNames[IntelRegisters.ENSF], false.toDouble())
			nonBlockError(ErrorType.STACK_UNDERFLOW)
			return 0
		}
		val element = stack[topIndex]
		stack[topIndex] = null
		topIndex--
		return element!!
	}

	/**
	 * Returns the entire stack as an array, including null elements for empty slots.
	 *
	 * @return An array representation of the stack.
	 */
	fun inspect(): Array<Long?> {
		return stack
	}

	/**
	 * Returns the top element of the stack without removing it.
	 *
	 * @return The top element.
	 * @throws EmptyStackException If the stack is empty.
	 */
	fun peek(): Long {
		if (isEmpty()) {
			registers.write(intelNames[IntelRegisters.ENSF], false.toDouble())
			nonBlockError(ErrorType.STACK_UNDERFLOW)
			return 0

		}
		return stack[topIndex]!!
	}

	/**
	 * Checks if the stack is empty.
	 *
	 * @return `true` if the stack is empty, `false` otherwise.
	 */
	fun isEmpty(): Boolean {
		return topIndex == -1
	}

	/**
	 * Checks if the stack is full.
	 *
	 * @return `true` if the stack is full, `false` otherwise.
	 */
	fun isFull(): Boolean {
		return topIndex == maxSize - 1
	}
}