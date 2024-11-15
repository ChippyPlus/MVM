package processes

import vm.exceptions.VmExceptions
import vm.exceptions.VmExceptions.StackOverflowException

data class Stack(val maxSize: Int) {  // Or Long, if you need a larger stack

	private val stackArray = arrayOfNulls<Long>(maxSize)
	private var top = -1  // Index of the top element

	fun push(value: Long) {
		if (top == maxSize - 1) {
			throw StackOverflowException() // Or handle differently (error flag)
		}
		stackArray[++top] = value
	}

	fun pop(): Long {
		if (top == -1) {
			throw VmExceptions.EmptyStackException()  // Or handle differently
		}

		val value = stackArray[top]!!
		stackArray[top--] = null // Clear the popped element and decrement top.
		return value
	}

	fun peek(): Long {
		if (top == -1) {
			throw VmExceptions.EmptyStackException()  // Or handle differently
		}

		val value = stackArray[top]!!
		return value
	}


	// Optional helper functions

	fun isEmpty(): Boolean = top == -1

	fun isFull(): Boolean = top == maxSize - 1

	// For debugging
	fun printStack() {
		println("Stack:")
		for (i in top downTo 0) {
			println("[${i}]: ${stackArray[i]}")
		}
	}
}