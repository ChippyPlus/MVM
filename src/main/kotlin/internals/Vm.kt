package internals

import config
import data.memory.InternalMemory
import data.registers.RegisterType
import data.registers.Registers
import data.registers.read
import environment.ExecuteLib
import environment.VMErrors
import environment.vfs.Vfs
import helpers.Helpers
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

class Vm {
	val helpers = Helpers(this)
	val libExecute = ExecuteLib(this)
	val internalMemory = InternalMemory(this)
	val errors = VMErrors(this)
	val registers = Registers(this)
	val dataTransfer = DataTransfer(this)
	val arithmetic = Arithmetic(this)
	val bitwise = Bitwise(this)
	val stackOperations = StackOperations(config?.stackSize ?: 12)
	val controlFlow = ControlFlow(this)
	val memory = Memory(this)
	val systemCall = SystemCall()
	val ioAbstractions = IoAbstractions(this)
	val strings = Strings() // Needed for Strings.str. It's very important
	val functions = Functions()
	val misc = Misc(this)
	val xFloats = XFloats()
	var pc: Long by Pc(vm = this)
	var libPc = 0L
	val vfs = Vfs()
}


class Pc(val vm: Vm) {

	init {
		vm.registers.write(RegisterType.I8, 0)
	}


	operator fun getValue(thisRef: Any?, property: KProperty<*>): Long {

		return RegisterType.I8.read(vm)
	}

	operator fun setValue(thisRef: Any?, property: KProperty<*>, value: Long) = kotlin.run {
		vm.registers.write(RegisterType.I8, value)
	}

	override fun toString(): String = RegisterType.I8.read(vm).toString()
	operator fun plus(a: Long): Long = a + RegisterType.I8.read(vm)
	operator fun plus(a: Int): Long = a.toLong() + RegisterType.I8.read(vm)
	operator fun minus(a: Long): Long = a - RegisterType.I8.read(vm)
	operator fun minus(a: Int): Long = a.toLong() - RegisterType.I8.read(vm)
	fun toLong(): Long = RegisterType.I8.read(vm)

}