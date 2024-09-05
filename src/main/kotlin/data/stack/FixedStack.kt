package org.example.data.stack

import org.example.errors

class FixedStack(private val maxSize: Int) {
    private val stack = Array<Int?>(maxSize) { null }
    private var topIndex = -1

    fun push(element: Int) {
        if (isFull()) {
            errors.StackOverflowException()
        }
        topIndex++
        stack[topIndex] = element
    }

    fun pop(): Int {
        if (isEmpty()) {
            errors.EmptyStackException()
        }
        val element = stack[topIndex]
        stack[topIndex] = null
        topIndex--
        return element!!
    }

    fun peek(): Int {
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