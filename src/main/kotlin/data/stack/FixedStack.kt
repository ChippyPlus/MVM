package data.stack

import data.flags.enumIdenifiers.StackUsingTypeType
import data.flags.stackUsingTypeFlag
import errors

/**
 * A fixed-size stack implementation using an array.
 *
 * @property maxSize The maximum number of elements that the stack can hold.
 *
 * @constructor Creates a new [FixedStack] with the specified maximum size.
 */
class FixedStack(private val maxSize: Int) {
    private val longStack = Array<Long?>(maxSize) { null }
    private val doubleStack = Array<Double?>(maxSize) { null }

    private var topIndex = -1

    /**
     * Pushes an element onto the stack.
     *
     * @param element The element to push onto the stack.
     * @throws StackOverflowException If the stack is full.
     */
    fun push(element: Number) {
        if (isFull()) {
            errors.StackOverflowException()
        }
        topIndex++
        if (stackUsingTypeFlag.read() == StackUsingTypeType.Long) {
            longStack[topIndex] = element.toLong()
        } else if (stackUsingTypeFlag.read() == StackUsingTypeType.Double) {
            doubleStack[topIndex] = element.toDouble()
        }
    }

    /**
     * Pops the top element from the stack and returns it.
     *
     * @return The popped element.
     * @throws EmptyStackException If the stack is empty.
     */
    fun pop(): Number {
        if (isEmpty()) {
            errors.EmptyStackException()
        }
        val element: Number? = if (stackUsingTypeFlag.read() == StackUsingTypeType.Long) {
            longStack[topIndex]
        } else if (stackUsingTypeFlag.read() == StackUsingTypeType.Double) {
            doubleStack[topIndex]
        } else {
            null
        }

        longStack[topIndex] = null
        topIndex--
        return element!!
    }

    /**
     * Returns the entire stack as an array, including null elements for empty slots.
     *
     * @return An array representation of the stack.
     */
    fun inspect(): Array<out Any?>? {
        val output: Array<out Any?> = if (stackUsingTypeFlag.read() == StackUsingTypeType.Long) {
            longStack
        } else if (stackUsingTypeFlag.read() == StackUsingTypeType.Double) {
            doubleStack
        } else {
            Array(maxSize) { "ERROR!!!!" }
        }
        return output
    }

    /**
     * Returns the top element of the stack without removing it.
     *
     * @return The top element.
     * @throws EmptyStackException If the stack is empty.
     */
    fun peek(): Number? {
        if (isEmpty()) {
            errors.EmptyStackException()
        }
        return if (stackUsingTypeFlag.read() == StackUsingTypeType.Long) {
            longStack[topIndex]!!
        } else if (stackUsingTypeFlag.read() == StackUsingTypeType.Double) {
            doubleStack[topIndex]!!
        } else {
            null
        }
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