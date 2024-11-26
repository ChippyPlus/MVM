package internals.instructions.dataTransfer

import data.memory.MemoryAddress
import data.memory.MemoryValue
import data.registers.RegisterType.*
import data.registers.read
import data.registers.write
import internals.Vm
import kotlin.test.Test
import kotlin.test.assertEquals


class DataTransferTest {
	val vm = Vm()
	val dt = DataTransfer(vm)

	@Test
	fun `Move Test Declare Register`() {
		G1.write(vm, 20)
		dt.mov(G1, G2)
		assertEquals(20, G2.read(vm))
	}

	@Test
	fun `Move Test Override Register`() {
		G1.write(vm, 20)
		G2.write(vm, 40)
		dt.mov(G1, G2)
		assertEquals(20, G2.read(vm))
	}

	@Test
	fun `Is Register Null Test Positive`() {
		G1.write(vm, 20)
		dt.inr(G3)
		assertEquals(1, R6.read(vm))
	}

	@Test
	fun `Is Register Null Test Negative`() {
		G1.write(vm, 20)
		dt.inr(G1)
		assertEquals(0, R6.read(vm))
	}

	@Test
	fun `Deallocate Memory Test General`() {
		vm.internalMemory.memory[MemoryAddress(20)] = MemoryValue(999)
		G1.write(vm, 20)
		dt.dealloc(G1)
		assertEquals(MemoryValue(null), vm.internalMemory.memory[MemoryAddress(20)])
	}

	@Test
	fun `Swap Register Test General`() {
		G1.write(vm, 20)
		G2.write(vm, 40)
		dt.swp(G1, G2)
		assertEquals(40, G1.read(vm))
		assertEquals(20, G2.read(vm))
	}

	@Test
	fun `Load Literal Test General`() {
		dt.lit(G10, 35)
		assertEquals(35, G10.read(vm))
	}

}