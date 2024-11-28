package kilb

import data.registers.RegisterType
import data.registers.read
import internals.Vm
import os

class Arrays(val vm: Vm) {
	val h = vm.heap!!
	fun size() {
		val meta = vm.registers.read(RegisterType.F1)
		val count = vm.heap!!.get(meta + 1)
		vm.stackOperations.internalStack.push(count)
	}

	fun create() {
		val size = RegisterType.F1.read(vm)
		val startAddr = h.alloc(size.toInt() + 1)
		h.set(startAddr, size)
		sreturn(vm, startAddr)
	}

	fun get() {
		val stAddr = RegisterType.F1.read(vm)
		val size = h.get(stAddr)
		val minAddr = stAddr + 1
		println("-----------------------------------------------------------------------------")
		println("OS M = ${os.mainMemory.toList().subList(0, 30).joinToString()}")

		val index = RegisterType.F2.read(vm)
		val value = RegisterType.F3.read(vm)

		if (index >= size) {
			println("Index = $index, size - 2 = ${size - 2}")
			println("NOO!!!")
			return
		}
		h.set(minAddr + index, value)

		println("OS M = ${os.mainMemory.toList().subList(0, 30).joinToString()}")
	}

	fun add() {
		val stAddr = RegisterType.F1.read(vm)
		// F1 = addr
		// F2 = index
		// F3 = value
		val size = h.get(stAddr)
		val minAddr = stAddr + 1

		val index = RegisterType.F2.read(vm)
		val value = RegisterType.F3.read(vm)

		if (index >= size) {
			println("Index = $index, size - 2 = ${size - 2}")
			println("ERRRORRR!!! FOR ADD RAY!")
			return
		}
		h.set(minAddr + index, value)
	}


//	fun createLinked(vm: Vm) {
//
//	}


}