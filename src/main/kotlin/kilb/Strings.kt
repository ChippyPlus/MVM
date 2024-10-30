package kilb

import data.memory.MemoryAddress
import data.registers.IntelRegisters
import data.registers.RegisterType
import data.registers.intelNames
import helpers.readRegisterString
import helpers.toLong
import helpers.writeClosestString
import helpers.writeStringSpecInMemory
import internalMemory
import registers
import vm

class Strings {
	fun strcmp() {
		registers.write(intelNames[IntelRegisters.ENSF], true.toLong())
		val s1 = readRegisterString(RegisterType.F1)
		val s2 = readRegisterString(RegisterType.F2)

//		if (s1 == s2) registers.write(register = R4, value = 0)
//		else registers.write(register = R4, value = 1)

		if (s1 == s2) vm.stackOperations.internalStack.push(true.toLong())
		else vm.stackOperations.internalStack.push(false.toLong())


	}

	fun strcat() {
		registers.write(intelNames[IntelRegisters.ENSF], true.toLong())
		val s1: String = readRegisterString(register = RegisterType.F1)
		val s2: Comparable<String> = readRegisterString(register = RegisterType.F2)
		val location = writeClosestString(string = (s1 + s2))
//		registers.write(R4, location)
		vm.stackOperations.internalStack.push(location)

	}

	fun strcpy() {
		registers.write(intelNames[IntelRegisters.ENSF], true.toLong())
		val string: String = readRegisterString(register = RegisterType.F1)
		val destinationAddress: Long = registers.read(register = RegisterType.F2)
		writeStringSpecInMemory(string = string, destinationAddress = MemoryAddress(address = destinationAddress))
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