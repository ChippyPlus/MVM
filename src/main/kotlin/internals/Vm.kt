package internals

import config
import environment.vfs.Vfs
import internals.instructions.arithmetic.Arithmetic
import internals.instructions.bitwise.Bitwise
import internals.instructions.controlFlow.ControlFlow
import internals.instructions.dataTransfer.DataTransfer
import internals.instructions.functions.Functions
import internals.instructions.ioAbstractions.IoAbstractions
import internals.instructions.memory.Memory
import internals.instructions.misc.Misc
import internals.instructions.stackOperations.StackOperations
import internals.instructions.strings.Strings
import internals.instructions.xFloats.XFloats
import internals.systemCalls.SystemCall
import kotlin.reflect.KProperty

open class Vm {
	val dataTransfer = DataTransfer()
	val arithmetic = Arithmetic()
	val bitwise = Bitwise()
	val stackOperations = StackOperations(config?.stackSize ?: 12)
	val controlFlow = ControlFlow()
	val memory = Memory()
	val systemCall = SystemCall()
	val ioAbstractions = IoAbstractions()
	val strings = Strings()
	val functions = Functions()
	val misc = Misc()
	val xFloats = XFloats()
	var pc: Long by Pc()
	var libPc = 0L
	val vfs = Vfs()
}


class Pc(private var count: Long = 0L) {
	operator fun getValue(thisRef: Any?, property: KProperty<*>): Long {
		return count
	}

	operator fun setValue(thisRef: Any?, property: KProperty<*>, value: Long) = kotlin.run { count = value }

	override fun toString(): String = count.toString()
	operator fun plus(a: Long): Long = a + count
	operator fun plus(a: Int): Long = a.toLong() + count
	operator fun minus(a: Long): Long = a - count
	operator fun minus(a: Int): Long = a.toLong() - count
	fun toLong(): Long = count

}