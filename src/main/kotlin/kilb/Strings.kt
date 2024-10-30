package kilb

import data.memory.MemoryAddress
import data.registers.IntelRegisters
import data.registers.RegisterType
import data.registers.RegisterType.R4
import data.registers.intelNames
import helpers.readRegisterString
import helpers.toLong
import helpers.writeClosestString
import helpers.writeStringSpecInMemory
import internalMemory
import internals.instructions.strings.Strings
import registers

class Strings {
	fun strcmp(string1: RegisterType, string2: RegisterType) {
		registers.write(intelNames[IntelRegisters.ENSF], true.toLong())
		val s1 = readRegisterString(string1)
		val s2 = readRegisterString(string2)

		if (s1 == s2) registers.write(register = R4, value = 0)
		else registers.write(register = R4, value = 1)


	}

	fun strcat(string1: RegisterType, string2: RegisterType) {
		registers.write(intelNames[IntelRegisters.ENSF], true.toLong())
		val s1: String = readRegisterString(register = string1)
		val s2: Comparable<String> = readRegisterString(register = string2)
		val location = writeClosestString(string = (s1 + s2))
		registers.write(R4, location)
	}

	fun strcpy(source: RegisterType, destination: RegisterType) {
		registers.write(intelNames[IntelRegisters.ENSF], true.toLong())
		val string: String = readRegisterString(register = source)
		val destinationAddress: Long = registers.read(register = destination)
		writeStringSpecInMemory(string = string, destinationAddress = MemoryAddress(address = destinationAddress))
	}

	fun Strings.strlen(addressRegister: RegisterType) {
		registers.write(intelNames[IntelRegisters.ENSF], true.toLong())

		var index: Long = 0L
		while (true) {
			val byte = internalMemory.read(MemoryAddress(registers.read(addressRegister) + index))
			if (byte.value?.equals(0L) ?: (false)) break
			index++
		}
		registers.write(register = R4, value = index)
	}
}