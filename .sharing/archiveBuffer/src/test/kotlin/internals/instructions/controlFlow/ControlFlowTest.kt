package internals.instructions.controlFlow

import data.registers.RegisterType
import data.registers.write
import internals.Vm
import kotlin.test.Test
import kotlin.test.assertEquals

class ControlFlowTest {
	val vm = Vm()
	val i4 = RegisterType.I4
	val controlFlow = ControlFlow(vm)

	@Test
	fun `Unconditional Jump Test`() {
		controlFlow.jmp(20)
		assertEquals(20L, vm.pc)
	}

	@Test
	fun `Jump if Zero test if positive`() {
		i4.write(vm, 0L)
		controlFlow.jz(20)
		assertEquals(20L, vm.pc)
		vm.pc = 0
	}

	@Test
	fun `Jump if Zero test if negative`() {
		i4.write(vm, 1L)
		controlFlow.jz(20)
		assertEquals(0L, vm.pc)
		vm.pc = 0
	}

	@Test
	fun `Jump not Zero if positive`() {
		i4.write(vm, 1L)
		controlFlow.jnz(20)
		assertEquals(20L, vm.pc)
		vm.pc = 0
	}

	@Test
	fun `Jump not Zero if negative`() {
		i4.write(vm, 0L)
		controlFlow.jnz(20)
		assertEquals(0L, vm.pc)
		vm.pc = 0
	}


}