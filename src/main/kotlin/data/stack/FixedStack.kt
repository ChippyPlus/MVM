package data.stack

import config
import data.registers.IntelRegisters
import data.registers.intelNames
import environment.errorsCatchable.ErrorType
import helpers.toLong
import internals.Vm

/**
 * A fixed-size stack implementation using an array.
 *
 * @property maxSize The maximum number of elements that the stack can hold.
 *
 * @constructor Creates a new [FixedStack] with the specified maximum size.
 */
class FixedStack(vm: Vm) {
	private val registers = vm.registers
	private val stack = Array<Long?>(config.stackSize) { _ -> null }
	private var topIndex = -1

	fun push(element: Long) {
		if (isFull()) {
			registers.write(intelNames[IntelRegisters.ENSF], false.toLong())
			registers.write(intelNames[IntelRegisters.ESF], ErrorType.STACK_OVERFLOW.code)
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
			registers.write(intelNames[IntelRegisters.ENSF], false.toLong())
			registers.write(intelNames[IntelRegisters.ESF], ErrorType.STACK_UNDERFLOW.code)
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
	@Suppress("unused")
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
			registers.write(intelNames[IntelRegisters.ENSF], false.toLong())
			registers.write(intelNames[IntelRegisters.ESF], ErrorType.STACK_UNDERFLOW.code)
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
		return topIndex == config.stackSize - 1
	}
}