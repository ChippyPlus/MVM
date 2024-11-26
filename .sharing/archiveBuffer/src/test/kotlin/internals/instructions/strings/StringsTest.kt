package internals.instructions.strings

import data.memory.MemoryAddress
import data.registers.RegisterType
import internals.Vm
import kotlin.streams.toList
import kotlin.test.Test
import kotlin.test.assertEquals

class StringsTest {
	val vm = Vm()
	val s = vm.strings

	@Test
	fun `Store String Test General`() {
		val testString = "I love puppies! Jk"
		s.str(targetAddress = RegisterType.G5, string = testString)
		testString.chars().toList().forEachIndexed { index, char ->
			assertEquals(vm.internalMemory.memory[MemoryAddress(index.toLong())]!!.value!!.toInt(), char)
		}
		assertEquals(
			vm.internalMemory.memory[MemoryAddress(testString.length.toLong())]!!.value,
			0
		) // Check null terminator explicitly
	}
}