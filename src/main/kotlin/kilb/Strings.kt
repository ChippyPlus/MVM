package kilb

import data.memory.MemoryAddress
import data.registers.IntelRegisters
import data.registers.RegisterType
import data.registers.intelNames
import environment.snapShotManager
import helpers.readRegisterString
import helpers.toLong
import helpers.writeClosestString
import helpers.writeStringSpecInMemory
import internals.Vm

class Strings(val vm: Vm) {
	val registers = vm.registers
	val internalMemory = vm.internalMemory
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
		snapShotManager.memoryRequestBlock(location..location + (s1 + s2).length)
//		registers.write(R4, location)
		vm.stackOperations.internalStack.push(location)

	}

	fun strcpy() {
		registers.write(intelNames[IntelRegisters.ENSF], true.toLong())
		val string: String = helpers.readRegisterString(register = RegisterType.F1)
		val destinationAddress: Long = registers.read(register = RegisterType.F2)
		snapShotManager.memoryRequestBlock(destinationAddress..destinationAddress + string.length)
		helpers.writeStringSpecInMemory(string = string, destinationAddress = MemoryAddress(address = destinationAddress))
	}

	fun strlen() {
		registers.write(intelNames[IntelRegisters.ENSF], true.toLong())

		var index: Long = 0L
		while (true) {
			val byte = internalMemory.read(MemoryAddress(registers.read(RegisterType.F1) + index))
			if (byte.value?.equals(0L) ?: (false)) break
			index++
		}
//		registers.write(register = R4, value = index)
		vm.stackOperations.internalStack.push(index)
	}
}