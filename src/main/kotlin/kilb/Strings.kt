package kilb

import data.registers.IntelRegisters
import data.registers.RegisterType
import data.registers.intelNames
import helpers.readRegisterString
import helpers.toLong
import helpers.writeClosestString
import helpers.writeStringSpecInMemory
import internals.Vm

class Strings(val vm: Vm) {
	val registers = vm.registers
	val helpers = vm.helpers

	fun strcmp() {
		registers.write(intelNames[IntelRegisters.ENSF], true.toLong())
		val s1 = helpers.readRegisterString(RegisterType.F1)
		val s2 = helpers.readRegisterString(RegisterType.F2)

		if (s1 == s2) registers.write(intelNames[IntelRegisters.EF], true.toLong())
		else registers.write(intelNames[IntelRegisters.EF], false.toLong())

	}

	fun strcat() {
		registers.write(intelNames[IntelRegisters.ENSF], true.toLong())
		val s1: String = helpers.readRegisterString(register = RegisterType.F1)
		val s2: Comparable<String> = helpers.readRegisterString(register = RegisterType.F2)
		val location = helpers.writeClosestString(string = (s1 + s2))
		vm.snapShotManager.memoryRequestBlock(location..location + (s1 + s2).length)
//		registers.write(R4, location)
		vm.stackOperations.internalStack.push(location)

	}

	fun strcpy() {
		registers.write(intelNames[IntelRegisters.ENSF], true.toLong())
		val string: String = helpers.readRegisterString(register = RegisterType.F1)
		val destinationAddress: Long = registers.read(register = RegisterType.F2)
		vm.snapShotManager.memoryRequestBlock(destinationAddress..destinationAddress + string.length)
		helpers.writeStringSpecInMemory(
			string = string,
			destinationAddress = destinationAddress
		)
	}

	fun strlen() {
		registers.write(intelNames[IntelRegisters.ENSF], true.toLong())

		var index: Long = 0L
		while (true) {
			val byte = vm.heap.get(registers.read(RegisterType.F1) + index)
			if (byte == 0L) break
			index++
		}
		vm.stackOperations.internalStack.push(index)
	}
}