package internals.instructions.memory

import data.memory.MemoryAddress
import data.memory.MemoryValue
import data.registers.RegisterType.*
import data.registers.read
import data.registers.write
import internals.Vm
import kotlin.test.Test
import kotlin.test.assertEquals

class MemoryTest {
	val vm = Vm()
	val m = Memory(vm)

	@Test
	fun `Store Test General`() {
		G1.write(vm, 352) // Value
		G2.write(vm, 29) // Address
		m.store(G1, G2)
		assertEquals(352, m.internalMemory.memory[MemoryAddress(29)]!!.value)
	}


	@Test
	fun `Load Test General`() {
		m.internalMemory.memory[MemoryAddress(82)] = MemoryValue(1457)
		G1.write(vm, 82)
		m.load(G1, G3)
		assertEquals(1457, G3.read(vm))
	}
}