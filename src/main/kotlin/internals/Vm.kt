package internals

import data.memory.Heap
import data.registers.RegisterType
import data.registers.Registers
import data.registers.read
import data.vfs.Vfs
import environment.VMErrors
import helpers.Helpers
import helpers.RuntimeStates
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
import kernel.ExecuteLib
import kernel.SnapShotManager
import kernel.systemCalls.SystemCall
import kotlinx.coroutines.runBlocking
import kotlin.reflect.KProperty

class Vm {
	val registers = Registers(this) // NOOOO!
	val snapShotManager = SnapShotManager(this) // NOOOO!!!
	val errors = VMErrors(this)
	val helpers = Helpers(this)
	val libExecute = ExecuteLib(this)
	val dataTransfer = DataTransfer(this)
	val arithmetic = Arithmetic(this)
	val bitwise = Bitwise(this)
	val stackOperations = StackOperations(this)
	val controlFlow = ControlFlow(this)
	val memory = Memory(this)
	val systemCall = SystemCall(this)
	val ioAbstractions = IoAbstractions(this)
	val strings = Strings(this) // Needed for Strings.str. It's very important
	val functions = Functions()
	val misc = Misc(this)
	val xFloats = XFloats(this)
	val pcInternal = Pc(vm = this)
	var pc: Long by pcInternal
	var libPc = 0L
	val vfs = Vfs()


	var heap: Heap? = null


	var runtimeState = RuntimeStates.RUNNING


}


class Pc(val vm: Vm) {

	init {
		vm.registers.write(RegisterType.I8, 0)
	}


	operator fun getValue(thisRef: Any?, property: KProperty<*>): Long {
		val value = RegisterType.I8.read(vm)
		return value
	}

	operator fun setValue(thisRef: Any?, property: KProperty<*>, value: Long) = runBlocking {
		if (value < 0) {
			vm.errors.InvalidPcValueException(value.toString())
		}
		vm.registers.write(RegisterType.I8, value)

	}

	override fun toString(): String = RegisterType.I8.read(vm).toString()
	operator fun plus(a: Long): Long = a + RegisterType.I8.read(vm)
	operator fun plus(a: Int): Long = a.toLong() + RegisterType.I8.read(vm)
	operator fun minus(a: Long): Long = a - RegisterType.I8.read(vm)
	operator fun minus(a: Int): Long = a.toLong() - RegisterType.I8.read(vm)
	fun toLong(): Long = RegisterType.I8.read(vm)
	fun toInt(): Int = RegisterType.I8.read(vm).toInt()
}