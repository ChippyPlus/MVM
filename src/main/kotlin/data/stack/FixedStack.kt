package data.stack

import errors

class FixedStack(private val maxSize: Int) {
    private val stack = Array<Long?>(maxSize) { null }
    private var topIndex = -1

    fun push(element: Long) {
        if (isFull()) {
            errors.StackOverflowException()
        }
        topIndex++
        stack[topIndex] = element
    }

    fun pop(): Long {
        if (isEmpty()) {
            errors.EmptyStackException()
        }
        val element = stack[topIndex]
        stack[topIndex] = null
        topIndex--
        return element!!
    }

    fun inspect(): Array<Long?> {
        return stack
    }

    fun peek(): Long {
        if (isEmpty()) {
            errors.EmptyStackException()
        }
        return stack[topIndex]!!
    }

    fun isEmpty(): Boolean {
        return topIndex == -1
    }

    fun isFull(): Boolean {
        return topIndex == maxSize - 1
    }
}